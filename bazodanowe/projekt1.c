#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <libpq-fe.h>

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
            fp = fopen(argv[2], "r");
            char znak;
            char slowo[257];
        znak = getc(fp);
        while(znak!=EOF){
        fscanf(fp, "%s", slowo);
        printf("\n%s\n",slowo);
        znak = getc(fp);
        licznik++;
        }
        fclose(fp);
        PQfinish(conn);
  		}
  		else printf("connection failed: %s\n", PQerrorMessage(conn));
 	 // w razie utraty po³±czenia wywo³anie
  	// PQreset(conn);
  	// zamyka op³±czenie i nawi±zuje je raz jeszcze
  	// z dotychczasowymi parametrami
	}
	return 0;
 }
