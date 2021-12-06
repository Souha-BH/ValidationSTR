//gcc train.c -lpthread then ./a.exe to execute
#include <time.h>
#include <pthread.h>
#include <semaphore.h>
#include <stdlib.h>

/* Les stations */
#define A 0
#define B 1
#define C 2
#define D 3
#define E 4
#define F 5

/* Code des threads */
void *MR1(void *arg); // de A vers D
void *MR2(void *arg); // de F vers A
void *MR3(void *arg); // de E vers B

pthread_mutex_t mut_AB;
pthread_mutex_t mut_BC;
pthread_mutex_t mut_CD;
pthread_mutex_t mut_FA;
pthread_mutex_t mut_EB;

/* Gestion du chemin de fer  */
void utiliser_le_segment(int station_depart, int station_arrivee)
{
    if ((station_depart == A) && (station_arrivee == B))
        pthread_mutex_lock(&mut_AB);
    else if ((station_depart == B) && (station_arrivee == C))
        pthread_mutex_lock(&mut_BC);
    else if ((station_depart == C) && (station_arrivee == D))
        pthread_mutex_lock(&mut_CD);
    else if ((station_depart == F) && (station_arrivee == A))
        pthread_mutex_lock(&mut_FA);
    else
        pthread_mutex_lock(&mut_EB);
}

void liberer_le_segment(int station_depart, int station_arrivee)
{
    if ((station_depart == A) && (station_arrivee == B))
        pthread_mutex_unlock(&mut_AB);
    else if ((station_depart == B) && (station_arrivee == C))
        pthread_mutex_unlock(&mut_BC);
    else if ((station_depart == C) && (station_arrivee == D))
        pthread_mutex_unlock(&mut_CD);
    else if ((station_depart == F) && (station_arrivee == A))
        pthread_mutex_unlock(&mut_FA);
    else
        pthread_mutex_unlock(&mut_EB);
}

int main(int argc, char *argv[])
{
    int i;
    pthread_t id;
    pthread_mutex_init(&mut_AB, 0);
    pthread_mutex_init(&mut_BC, 0);
    pthread_mutex_init(&mut_CD, 0);
    pthread_mutex_init(&mut_FA, 0);
    pthread_mutex_init(&mut_EB, 0);

    for (i = 0; i < 3; i++) // Créer autant de trains que l'on veut
    {
        pthread_create(&id, NULL, MR1, NULL);
        pthread_create(&id, NULL, MR2, NULL);
        pthread_create(&id, NULL, MR3, NULL);
    }

    pthread_exit(NULL);
}

void *MR1(void *arg)
{
    utiliser_le_segment(A, B);
    printf("Train %d : utilise segment AB \n", pthread_self());
    usleep(360000);
    liberer_le_segment(A, B);

    utiliser_le_segment(B, C);
    printf("Train %d : utilise segment BC \n", pthread_self());
    usleep(360000);
    liberer_le_segment(B, C);

    utiliser_le_segment(C, D);
    printf("Train %d : utilise segment CD \n", pthread_self());
    usleep(360000);
    liberer_le_segment(C, D);

    pthread_exit(NULL);
}

void *MR2(void *arg)
{
    utiliser_le_segment(F, A);
    printf("Train %d : utilise segment FA \n", pthread_self());
    usleep(360000);
    liberer_le_segment(D, B);

    pthread_exit(NULL);
}
void *MR3(void *arg)
{
    utiliser_le_segment(E, B);
    printf("Train %d : utilise segment EB \n", pthread_self());
    usleep(360000);
    liberer_le_segment(E, B);

    pthread_exit(NULL);
}
