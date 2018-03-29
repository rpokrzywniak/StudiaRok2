#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <libpq-fe.h>

void doSQL(PGconn *conn, char *command)
{
  PGresult *result;

  printf("%s\n", command);

  result = PQexec(conn, command);
  printf("status is     : %s\n", PQresStatus(PQresultStatus(result)));
  printf("#rows affected: %s\n", PQcmdTuples(result));
  printf("result message: %s\n", PQresultErrorMessage(result));

  switch(PQresultStatus(result)) {
  case PGRES_TUPLES_OK:
    {
      int n = 0, m = 0;
      int nrows   = PQntuples(result);
      int nfields = PQnfields(result);
      printf("number of rows returned   = %d\n", nrows);
      printf("number of fields returned = %d\n", nfields);
      for(m = 0; m < nrows; m++) {
	for(n = 0; n < nfields; n++)
	  printf(" %s = %s", PQfname(result, n),PQgetvalue(result,m,n));
	printf("\n");
      }
    }
  }
  PQclear(result);
}

void doHTML(PGconn *conn, char *command)
{
  PGresult *result;

  result = PQexec(conn, command);

  switch(PQresultStatus(result)) {
  case PGRES_TUPLES_OK:
    {
      int n = 0, m = 0;
      int nrows   = PQntuples(result);
      int nfields = PQnfields(result);
	printf("<table>\n");
	printf(" <tr>\n");
      for(m = 0; m < nfields; m++) {
		printf("  <th>%s</th>\n", PQfname(result, m));
	}
	printf(" </tr>\n");
	for(m = 0; m < nrows; m++) {
	printf(" <tr>\n");
	for(n = 0; n < nfields; n++)
	  printf("  <td>%s</td>\n", PQgetvalue(result,m,n));
	printf("</tr>\n");
      }
	printf("</table>");
    }
  }
  PQclear(result);
}

int main(int argc, char ** argv)
{
    if(argc<3){
	char logowanie[150]="host=localhost port=5432 dbname=projekt";
        char logowanie2[150]=" user=rpokrzywniak password=";
        strcat(logowanie,logowanie2);
        char pass[30]={'\0'};
        printf("Password: ");
        system("stty -echo");
        scanf("%s",pass);
        system("stty echo");
        system("clear");
        strcat(logowanie,pass);
  		PGconn *conn = PQconnectdb(logowanie);
 		// sprawdzamy status po³±czenia
  		if(PQstatus(conn) == CONNECTION_OK) {
           		printf("connection made\n");
   		 	// informacje o po³±czeniu
            		printf("PGDBNAME   = %s\n",PQdb(conn));
           		printf("PGUSER     = %s\n",PQuser(conn));
    			//printf("PGPASSWORD = %s\n",PQpass(conn));
            		printf("PGPASSWORD = ********\n");
            		printf("PGHOST     = %s\n",PQhost(conn));
            		printf("PGPORT     = %s\n",PQport(conn));
            		printf("OPTIONS    = %s\n",PQoptions(conn));

			FILE *fp;
			char plik[50]={0};
			char plik1[50]={0};
			printf("Podaj nazwe pliku .csv : ");
			scanf("%s",plik);
			strcpy(plik1,plik);
			strcat(plik1,".csv");
			printf("xd");
        		fp = fopen(plik1, "r");
			printf("xd");
        		char znak;
        		char slowo[257];
			int licznik=0;
        		znak = getc(fp);
        		while(znak!=EOF){
        			fscanf(fp, "%s", slowo);
        			znak = getc(fp);
        			licznik++;
        		}
        		fclose(fp);
			fp = fopen(plik1, "r");
			znak = getc(fp);
			char wiersz[257];
			int akt_wiersz=0;
			int Klicznik=0;
			int Klicznik_znaku=0;

			char drop[50]="DROP TABLE IF EXISTS ";
			strcat(drop,plik);
			doSQL(conn, drop);

			char create[257]="CREATE TABLE ";
			strcat(create,plik);
			strcat(create,"(");
			memset(wiersz,0,20);

			char insert[257]="INSERT INTO ";
			strcat(insert,plik);
			strcat(insert," VALUES(\'");
			char insertk[257]={0};
			strcpy(insertk,insert);
			int l=0;
			char kolumna[6][50]={0};
			while(znak!=EOF){
				if(znak=='\n'){
					if(akt_wiersz==0){
						strcat(create,wiersz);
						strcpy(kolumna[Klicznik],wiersz);
						strcat(create," VARCHAR(20))");
						doSQL(conn,create);
					}
					else{
						l=strlen(wiersz);
						if(l>20){
							memset(create,0,257);
							strcpy(create,"ALTER TABLE ");
							strcat(create,plik);
							strcat(create," ALTER COLUMN ");
							strcat(create,kolumna[Klicznik]);
							strcat(create," TYPE VARCHAR(");
							char str[10]={0};
							sprintf(str, "%d",l);
							strcat(create,str);
							strcat(create,");");
							doSQL(conn,create);
						}

						strcat(insert,wiersz);
						strcat(insert,"\');");
						doSQL(conn,insert);
						memset(insert,0,257);
						strcpy(insert,insertk);
					}
					memset(wiersz,0,257);
					akt_wiersz++;
					Klicznik=0;
					Klicznik_znaku=0;
				}
				else if(znak==';'){
					if(akt_wiersz==0){
						strcat(create,wiersz);
						strcpy(kolumna[Klicznik],wiersz);
						if(Klicznik==0) strcat(create," VARCHAR(20) UNIQUE, ");
						else strcat(create," VARCHAR(20), ");
					}
					else{
						l=strlen(wiersz);
						if(l>20){
							memset(create,0,257);
							strcpy(create,"ALTER TABLE ");
							strcat(create,plik);
							strcat(create," ALTER COLUMN ");
							strcat(create,kolumna[Klicznik]);
							strcat(create," TYPE VARCHAR(");
							char str[10]={0};
							sprintf(str, "%d",l);
							strcat(create,str);
							strcat(create,");");
							doSQL(conn,create);
						}
						strcat(insert,wiersz);
						strcat(insert,"\', \'");
					}
					memset(wiersz,0,257);
					Klicznik++;
					Klicznik_znaku=0;
				}
				else{
					wiersz[Klicznik_znaku]=znak;
					Klicznik_znaku++;
				}
				znak=getc(fp);
			}
				while(1){
				printf("\nNACISNIJ 1 ABY DODAC REKORD, 2 ABY WYPISAC TABELE, 3 ABY ZAKONCZYC PROGRAM: ");
				scanf("%d",&l);
				if(l==1){
					memset(insert,0,257);
					memset(wiersz,0,257);
					strcpy(insert,"INSERT INTO ");
					strcat(insert,plik);
					strcat(insert," VALUES(\'");
					printf("\n%s = ",kolumna[0]);
					scanf("%s",wiersz);
					strcat(insert,wiersz);
					strcat(insert,"\', \'");
					printf("\n%s = ",kolumna[1]);
					scanf("%s",wiersz);
					strcat(insert,wiersz);
					strcat(insert,"\', \'");
					printf("\n%s = ",kolumna[2]);
					scanf("%s",wiersz);
					strcat(insert,wiersz);
					strcat(insert,"\', \'");
					printf("\n%s = ",kolumna[3]);
					scanf("%s",wiersz);
					strcat(insert,wiersz);
					strcat(insert,"\', \'");
					printf("\n%s = ",kolumna[4]);
					scanf("%s",wiersz);
					strcat(insert,wiersz);
					strcat(insert,"\', \'");
					printf("\n%s = ",kolumna[5]);
					scanf("%s",wiersz);
					strcat(insert,wiersz);
					strcat(insert,"\');");
					doSQL(conn,insert);
				}
				if(l==2){
					memset(insert,0,257);
					strcpy(insert,"SELECT * FROM ");
					strcat(insert, plik);
					doSQL(conn,insert);
				}
				if(l==3){
					break;
				}
			}
			fclose(fp);

  		}
  		else printf("connection failed: %s\n", PQerrorMessage(conn));
        PQfinish(conn);
	}

	else{
	int licz;
        char logowanie[150]="host=localhost port=5432 dbname=";
        char logowanie2[150]=" user=rpokrzywniak password=";
        strcat(logowanie,argv[1]);
        strcat(logowanie,logowanie2);
        char pass[30]={'\0'};
        system("stty -echo");
        scanf("%s",pass);
        system("stty echo");
        strcat(logowanie,pass);
  		PGconn *conn = PQconnectdb(logowanie);
 		// sprawdzamy status po³±czenia
  		if(PQstatus(conn) == CONNECTION_OK) {
			for(licz=2;licz<argc;licz++){
      			FILE *fp;
			char plik[20];
			strcpy(plik,argv[licz]);
			strcat(plik,".csv");
        		fp = fopen(plik, "r");
        		char znak;
        		char slowo[257];
			int licznik=0;
        		znak = getc(fp);
        		while(znak!=EOF){
        			fscanf(fp, "%s", slowo);
        			znak = getc(fp);
        			licznik++;
        		}
        		fclose(fp);
			fp = fopen(plik, "r");
			znak = getc(fp);
			char wiersz[257];
			int akt_wiersz=0;
			int Klicznik=0;
			int Klicznik_znaku=0;

			char drop[50]="DROP TABLE IF EXISTS ";
			strcat(drop,argv[licz]);
			PQexec(conn, drop);

			char create[257]="CREATE TABLE ";
			strcat(create,argv[licz]);
			strcat(create,"(");
			memset(wiersz,0,20);

			char insert[257]="INSERT INTO ";
			strcat(insert,argv[licz]);
			strcat(insert," VALUES(\'");
			char insertk[257]={0};
			strcpy(insertk,insert);
			int l=0;
			char kolumna[6][50]={0};
			while(znak!=EOF){
				if(znak=='\n'){
					if(akt_wiersz==0){
						strcat(create,wiersz);
						strcpy(kolumna[Klicznik],wiersz);
						strcat(create," VARCHAR(20))");
						PQexec(conn,create);
					}
					else{
						l=strlen(wiersz);
						if(l>20){
							memset(create,0,257);
							strcpy(create,"ALTER TABLE ");
							strcat(create,argv[licz]);
							strcat(create," ALTER COLUMN ");
							strcat(create,kolumna[Klicznik]);
							strcat(create," TYPE VARCHAR(");
							char str[10]={0};
							sprintf(str, "%d",l);
							strcat(create,str);
							strcat(create,");");
							PQexec(conn,create);
						}

						strcat(insert,wiersz);
						strcat(insert,"\');");
						PQexec(conn,insert);
						memset(insert,0,257);
						strcpy(insert,insertk);
					}
					memset(wiersz,0,257);
					akt_wiersz++;
					Klicznik=0;
					Klicznik_znaku=0;
				}
				else if(znak==';'){
					if(akt_wiersz==0){
						strcat(create,wiersz);
						strcpy(kolumna[Klicznik],wiersz);
						if(Klicznik==0) strcat(create," VARCHAR(20) UNIQUE, ");
						else strcat(create," VARCHAR(20), ");
					}
					else{
						l=strlen(wiersz);
						if(l>20){
							memset(create,0,257);
							strcpy(create,"ALTER TABLE ");
							strcat(create,argv[licz]);
							strcat(create," ALTER COLUMN ");
							strcat(create,kolumna[Klicznik]);
							strcat(create," TYPE VARCHAR(");
							char str[10]={0};
							sprintf(str, "%d",l);
							strcat(create,str);
							strcat(create,");");
							PQexec(conn,create);
						}
						strcat(insert,wiersz);
						strcat(insert,"\', \'");
					}
					memset(wiersz,0,257);
					Klicznik++;
					Klicznik_znaku=0;
				}
				else{
					wiersz[Klicznik_znaku]=znak;
					Klicznik_znaku++;
				}
				znak=getc(fp);
			}
				PGresult *result;
                if(licz==2){
                    printf("<!DOCTYPE html>\n");
                    printf("<html>\n");
                    printf("<head>\n");
                    printf(" <meta charset=\"UTF-8\">\n");
                    printf(" <title>FAJNY PROGRAM</title>\n");
                    printf("<style>\n");
                    printf("table {\nfont-family: arial, sans-serif%c\nborder-collapse: collapse%c\nwidth: 100%%%c\n}\n",59,59,59);
                    printf("td, th {\nborder: 1px solid #dddddd%c\ntext-align: left%c/npadding: 8px%c\nwhite-space:nowrap%c\n}\n",59,59,59,59);
                    printf("tr:nth-child(even) {\nbackground-color: #dddddd%c\n}",59);
                    printf("</style>\n");
                    printf("</head>\n");
                    printf("<body>\n");
                }
				memset(insert,0,257);
				strcpy(insert,"SELECT * FROM ");
				strcat(insert, argv[licz]);
				doHTML(conn,insert);
				if(licz==argc-1){
                    printf("</body>");
                    printf("</html>");
				}
				fclose(fp);

  		}
  		}
  		else printf("connection failed: %s\n", PQerrorMessage(conn));
        PQfinish(conn);
	}
	return 0;
 }
