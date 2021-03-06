% male(male) %
male(james). male(harold). male(clayburn). male(samuel). male(bill). male(herschel).
male(joseph). male(grant). male(jim). male(ira). male(arthur). male(jay). male(paul).
male(booker). male(joe). male(ben). male(larry). male(doug). male(david). male(mike).
male(woodrow). male(steven).

% female(female) %
female(thelma). female(margaret). female(phyllis). female(hilda). female(blanche).
female(narsie).  female(elsie). female(loraine). female(charlotte). female(yvonne).
female(millicent). female(betty). female(shirley). female(brenda). female(kay).
female(donna). female(deanna). female(bonnie). female(ronnie). female(clara).

% husbandof(husband, wife) %
husbandof(james, thelma). husbandof(joseph, blanche). husbandof(grant, margaret).
husbandof(jim, phyllis). husbandof(harold, elsie). husbandof(ira, hilda).
husbandof(clayburn, loraine). husbandof(samuel, charlotte). husbandof(arthur, narsie).
husbandof(herschel, yvonne).

% fatherof(father, child) %
fatherof(james, margaret). fatherof(james, harold). fatherof(james, clayburn). fatherof(james, phyllis).
fatherof(james, hilda). fatherof(james, samuel). fatherof(james, bill). fatherof(joseph, ira). fatherof(joseph, narsie).
fatherof(joseph, herschel). fatherof(grant, jay). fatherof(grant, millicent). fatherof(grant, betty). fatherof(grant, paul).
fatherof(harold, shirley). fatherof(harold, booker). fatherof(harold, brenda). fatherof(harold, kay). fatherof(ira, donna).
fatherof(ira, joe). fatherof(ira, ben). fatherof(ira, larry). fatherof(ira, grant). fatherof(ira, deanna). fatherof(jim, bonnie).
fatherof(clayburn, jim). fatherof(clayburn, doug). fatherof(clayburn, david). fatherof(samuel, ronnie). fatherof(samuel, mike).
fatherof(arthur, woodrow). fatherof(arthur, clara). fatherof(herschel, steven). fatherof(herschel, jane).

% motherof(mother, child) %
motherof(Mother, Child) :-
    fatherof(Father, Child), husbandof(Father, Mother).

% wifeof(wife, husband) %
wifeof(Wife, Husband) :-
    husbandof(Husband, Wife).

% sibling(child, child) %
siblingof(Child1, Child2) :-
    Child1 \= Child2,
    fatherof(Father, Child1),
    fatherof(Father, Child2);
    motherof(Mother, Child1),
    motherof(Mother, Child2).

parentof(Parent, Child) :-
    fatherof(Parent, Child) ;
    motherof(Parent, Child) .

% grandparentof(grandparent, child) % 
grandparentof(Grandparent, Child) :-
    fatherof(Father, Child), fatherof(Grandparent, Father) ;
    fatherof(Father, Child), motherof(Grandparent, Father) ;
    motherof(Mother, Child), motherof(Grandparent, Mother) ;
    motherof(Mother, Child), fatherof(Grandparent, Mother) .

% uncleof(uncle, child) %
uncleof(Uncle, Child) :-
    fatherof(Father, Child), siblingof(Father, Uncle), male(Uncle) ,
    Father \= Uncle ;
    motherof(Mother, Child), siblingof(Mother, Uncle), male(Uncle) .

% auntof(aunt, child) %
auntof(Aunt, Child) :-
    motherof(Mother, Child), siblingof(Mother, Aunt), female(Aunt) ,
    Mother \= Aunt ;
    fatherof(Father, Child), siblingof(Father, Aunt), female(Aunt) .

% nephewof(nephew, person) %
nephewof(Nephew, Person) :-
    uncleof(Person, Nephew), male(Nephew) ;
    auntof(Person, Nephew), male(Nephew) .

% nieceof(niece, person) %
nieceof(Niece, Person) :-
    uncleof(Person, Niece), female(Niece) ;
    auntof(Person, Niece), female(Niece) .

% cousinon(person, person) %
cousinof(PersonA, PersonB) :-
    parentof(ParentA, PersonA) ,
    parentof(ParentB, PersonB) ,
    ParentA \= ParentB ,
    siblingof(ParentA, ParentB) .

% soninlawof(son, parent) %
soninlawof(Son, Parent) :-
    male(Son) ,
    wifeof(Wife, Son),
    parentof(Parent, Wife) .

% daughterinlawof(daughter, parent) %
daughterinlawof(Daughter, Parent) :-
    female(Daughter) ,
    husbandof(Husband, Daughter) ,
    parentof(Parent, Husband) .

% brotherinlawof(brother, sibling) %
brotherinlawof(Brother, Sibling) :-
    male(Brother) ,
    wifeof(Wife, Brother) ,
    siblingof(Wife, Sibling) ,
    Sibling \= Wife .

% sisterinlawof(sister, sibling) %
sisterinlawof(Sister, Sibling) :-
    female(Sister) ,
    husbandof(Husband, Sister) ,
    siblingof(Husband, Sibling) ,
    Sibling \= Husband .