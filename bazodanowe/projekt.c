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

int main(int argc, char ** argv)
{
    if(argc<3) printf("Za malo argumentow");
	else{
        char logowanie[150]="host=localhost port=5432 dbname=";
        char logowanie2[150]=" user=rpokrzywniak password=";
        strcat(logowanie,argv[1]);
        strcat(logowanie,logowanie2);
        char pass[30]={'\0'};
        printf("Password: ");
        system("stty -echo");
        scanf("%s",pass);
        system("stty echo");
        system("clear");
        strcat(logowanie,pass);
        printf("\n%s\n",logowanie);
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
			char plik[20];
			strcpy(plik,argv[2]);
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
			char wiersz[20];
			int akt_wiersz=0;
			int Klicznik=0;
			int Klicznik_znaku=0;

			char drop[50]="DROP TABLE ";
			strcat(drop,argv[2]);
			doSQL(conn, drop);

			char create[257]="CREATE TABLE ";
			strcat(create,argv[2]);
			strcat(create,"(");


			char insert[257];
			//sprintf(insert, "INSERT INTO %s VALUES(\'%s\', \'%s\', \'%s\', \'%s\', \'%s\', \'%s\')",value,name);


			while(znak!=EOF){
				if(znak=='\n'){
					if(akt_wiersz==0){
						strcat(create,wiersz);
						strcat(create," VARCHAR(20));");
					}

					memset(wiersz,0,20);
					akt_wiersz++;
					Klicznik=0;
					Klicznik_znaku=0;
				}
				else if(znak==';'){
					if(akt_wiersz==0){
						strcat(create,wiersz);
						if(Klicznik==0) strcat(create," VARCHAR(20) UNIQUE, ");
						else strcat(create," VARCHAR(20), ");
					}

					memset(wiersz,0,20);
					Klicznik++;
					Klicznik_znaku=0;
				}
				else{
					wiersz[Klicznik_znaku]=znak;
					Klicznik_znaku++;
				}
				znak=getc(fp);
			}
			doSQL(conn, create);
			fclose(fp);
  		}
  		else printf("connection failed: %s\n", PQerrorMessage(conn));
 	 // w razie utraty po³±czenia wywo³anie
  	// PQreset(conn);
  	// zamyka op³±czenie i nawi±zuje je raz jeszcze
  	// z dotychczasowymi parametrami

        PQfinish(conn);
	}
	return 0;
 }
