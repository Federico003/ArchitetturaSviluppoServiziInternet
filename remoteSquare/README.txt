Si realizzi un'applicazione distribuita che permetta di calcolare il quadrato dei numeri inseriti dall'utente a terminale.

L'applicazione deve avere la seuente interfaccia:

    java RemoteSquareClient hostname porta

dove hostname è il nome dell'host dove risede il Server e porta è il numero di porta a cui esso è associato.

Per prima cosa, il Client deve interfacciarsi con l'utente, da cui riceve, via terminale un numero intero N.
Il Client deve quindi trasmettere il numero N al Server, che a sua volta dovrà occuparsi di calcolare il quadrato di N
(ovverosia N*N) e di restituirlo al Client, che lo stamperà a video.

Al termine di ogni richiesta, il Client dovrà attendere che l'utente inserisca un nuovo numero.
Il Client dovràterminae nel caso l'utente inserisca la stringa "fine".