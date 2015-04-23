% students with fever
hasfever(frank). hasfever(gary). hasfever(barbara).  hasfever(chris). hasfever(norma). hasfever(connie).
hasfever(don). hasfever(amanda). hasfever(fred).

% students with cough
hascough(frank). hascough(chris). hascough(don). hascough(fred).

% students with conjunctivitis
hasconjunct(frank). hasconjunct(chris). hasconjunct(fred).

% students with runny nose
hasrunnynose(frank). hasrunnynose(chris). hasrunnynose(mike). hasrunnynose(fred).

% students with rash
hasrash(frank). hasrash(norma). hasrash(amanda). hasrash(fred).

% students with swollen glands
hasswollenglands(frank). hasswollenglands(barbara). hasswollenglands(don). hasswollenglands(fred). hasswollenglands(jon).

% students with body ache
hasbodyache(chris). hasbodyache(norma). hasbodyache(amanda). hasbodyache(fred).

% students with chills
haschills(chris). haschills(norma). haschills(mike). haschills(amanda). haschills(fred). haschills(jon).

%students with sore throat
hassorethroat(chris). hassorethroat(mike). hassorethroat(fred).

% students with sneezing
hassneezing(mike). hassneezing(fred).

% students with headache
hasheadache(connie). hasheadache(mike).

% symptoms for measles
hasMeasles(Patient) :-
    hasfever(Patient),
    hascough(Patient),
    hasconjunct(Patient),
    hasrunnynose(Patient),
    hasrash(Patient).

% symptoms for measles
hasGermanMeasles(Patient) :-
    hascough(Patient),
    hasrunnynose(Patient),
    hasrash(Patient),
    hasheadache(Patient).

% symptoms for measles
hasFlu(Patient) :-
    hasfever(Patient),
    hascough(Patient),
    hasconjunct(Patient),
    hasrunnynose(Patient),
    hasbodyache(Patient),
    hasheadache(Patient),
    haschills(Patient),
    hassorethroat(Patient).

% symptoms for measles
hasCommonCold(Patient) :-
    hasrunnynose(Patient),
    hasheadache(Patient),
    haschills(Patient),
    hassorethroat(Patient),
    hassneezing(Patient).

% symptoms for measles
hasMumps(Patient) :-
    hasfever(Patient),
    hasswollenglands(Patient).

% symptoms for measles
hasChickenPox(Patient) :-
    hasfever(Patient),
    hasrash(Patient),
    hasbodyache(Patient),
    haschills(Patient).

hasDisease(Patient) :-
    hasMeasles(Patient) ;
    hasGermanMeasles(Patient) ;
    hasFlu(Patient) ;
    hasCommonCold(Patient) ;
    hasMumps(Patient) ;
    hasChickenPox(Patient).

% Students that have a disease should stay home
shouldStayHome(X) :-
    hasChickenPox(X) ;
    hasMeasles(X) ;
    hasGermanMeasles(X) ;
    hasMumps(X).

hasCancerRisk(Patient) :-
    haschills(Patient),
    hasswollenglands(Patient),
    not(hasfever(Patient)),
    not(hascough(Patient)),
    not(hasconjunct(Patient)),
    not(hasrunnynose(Patient)),
    not(hasrash(Patient)),
    not(hasbodyache(Patient)),
    not(hasheadache(Patient)),
    not(hassorethroat(Patient)),
    not(hassneezing(Patient)).