proc contents data=DATA1;
proc contents data=DATA2;
run;

data DATA1;
set DATA1;
if iq >= 120 then genius = 1;
else genius = 0;

if gpa >= 3.25 and act >= 22 then honors = 1;
else honors = 0;

proc contents data=DATA1;
run;

proc print data=DATA1;
run;

proc sort data=DATA1;
by id;
proc sort data=DATA2;
by id;
data MERGED;
merge DATA1 DATA2;
by id;
run;

proc contents data=MERGED;
run;

proc means data=DATA1;
var iq gpa age act;
run;

proc sort data=DATA1;
by state;
proc means data=DATA1;
var iq gpa age act;
by state;
run;

proc freq data=MERGED;
tables gender state party major;
run;

proc freq data=DATA2;
tables party;
run;

proc freq data=DATA1;
tables state;
run;

proc freq data=MERGED;
tables gender*genius;
run;

proc freq data=MERGED;
tables honors*state;
run;

proc freq data=MERGED;
tables party*state;
run;

proc freq data=MERGED;
tables gender*genius;
run;

proc freq data=MERGED;
tables state*party;
run;

proc sort data=MERGED;
by state;
proc means data=MERGED;
var iq;
by state;
run;

proc means data=MERGED;
var age;
by state;
run;

proc means data=MERGED;
var gpa;
by state;
run;

proc freq data=MERGED;
tables religion;
run;

proc freq data=MERGED;
tables religion*state;
run;

proc freq data=MERGED;
tables gender*major;
run;

proc freq data=MERGED;
tables honors*act;
run;

proc freq data=MERGED;
tables honors*state;
run;

proc freq data=MERGED;
table genius*religion;
run;
