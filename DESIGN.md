# Calculating the Expected Utility of Charities


![alt text](https://github.com/DimitrivC/Final_Project/blob/master/doc/All%20Activities%20Final%20Project.png)


## Login_Activity
(i) The user can reach this via:\
&nbsp;&nbsp;&nbsp;&nbsp;(a) Starting the app. This is the first Activity.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: Specify in manifest that it is the launcher Activity.\
&nbsp;&nbsp;&nbsp;&nbsp;(b) By logging out from Firebase via Main_Activity; Calculate_Activity; Add_Saved_Charity_Activity; Save_New_Charity_Activity (so, all other activities with the exeption of Create_New_Account_Activity).\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: nothing in particular (see other activities).\
&nbsp;&nbsp;&nbsp;&nbsp;(c) By pressing backpressbutton via Main_Activity. But, this should be overrided: the user should then leave the app).
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: Override backpress in Main_Activity.\
&nbsp;&nbsp;&nbsp;&nbsp;(d) By pressing bakcpressbutton via Create_New_Account_Activity\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: nothing in particular.\
(ii) The user can:\
&nbsp;&nbsp;&nbsp;&nbsp;(1) Click on the information Icon. If she does, she will see a Dialog Fragment, with text explaining the app.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: Actionbar Icon; method to show Icon; method to handle events for Icon, in this case showing a Diolog Fragment with text.\
&nbsp;&nbsp;&nbsp;&nbsp;(2) Click on the Create New Account button if she has no account yet or wants to make another one. She will be forwarded to Create_New_Account_Activity (see below).\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: Button with text "Create New Account"; onClick function for if user clicks with intent to go to Create_New_Account_Activity.\
&nbsp;&nbsp;&nbsp;&nbsp;(3) Log in, via Firebase. She can log in via e-mail and password. If successful, she will be forwarded to Main_Activity. If not, she will be notified why not.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: box for user to enter e-mail; box to enter password; method to retrieve e-mail and password; method for signing in and if successful forward to Main_Activity, if unsuccessful notify user.\
(iii) Also required:\
&nbsp;&nbsp;&nbsp;&nbsp;(a) Check if the user is logged in via Firebase as soon as she reaches this Activity. If not, nothing happens (meaning she remains here). If she is, she will be redirected to Main_Activity.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: check auth state in onCreate; if statement in onStarte to forward user to Main_Activity if logged in.\
&nbsp;&nbsp;&nbsp;&nbsp;(b) Nice background\
&nbsp;&nbsp;&nbsp;&nbsp;(c) Nice picture which is somehow relevant to both calculating expected utility & Charities.\
&nbsp;&nbsp;&nbsp;&nbsp;(d) Title of app\
&nbsp;&nbsp;&nbsp;&nbsp;(e) Very brief description of app directly below title.\
&nbsp;&nbsp;&nbsp;&nbsp;(f) Save instance state
  
## Create_New_Account_Activity
(i) The user can reach this Activity via:\
&nbsp;&nbsp;&nbsp;&nbsp;(a) Login_Activity: by clicking the button "Create New Account."\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: nothing in particular, see Login_Activity.\
&nbsp;&nbsp;&nbsp;&nbsp;(b) Main_Activity: by clicking the backpressbutton if the user had created an account and then went to Main_Activity.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: nothing in particular (but, the user will be immediately directed back, so override back press in Main_Activity).\
(ii) The user can\
&nbsp;&nbsp;&nbsp;&nbsp;(1) Enter an e-mail suitable for Firebase.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: TextView; method for getting access to what is entered by user (onClick from (3)).\
&nbsp;&nbsp;&nbsp;&nbsp;(2) Enter a password suitable for Firebase.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: TextView; method for getting it (onClick from (3)).\
&nbsp;&nbsp;&nbsp;&nbsp;(3) Click on a button to create a New Account. If successful, she will be forwarded to Main_Activity. If unsuccessful, show reason,\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: onClick to get info: this is the method for getting the e-mail & passwords, and give that to: method to create user for FIrebase.\
&nbsp;&nbsp;&nbsp;&nbsp;(4) Click on an information Icon to show Dialog Fragment with information about the app (same as with Login_Activity).\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: Actionbar Icon; method to show Icon; method to handle events for Icon, which will show a Dialog Fragment with text. \
(iii) Also required:\
&nbsp;&nbsp;&nbsp;&nbsp;(a) Check to see if the user is logged in via Firebase as soon as she reaches this Activity. If not, she will be redirected to Login_Activity. If she is, nothing happens (so that she can stay on this page).\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: check auth state in onCreate (also to create new user); if statement in onStart to forward user to Main_Activity if logged in.\
&nbsp;&nbsp;&nbsp;&nbsp;(b) Nice background\
&nbsp;&nbsp;&nbsp;&nbsp;(c) Title of page "Create new account"\
&nbsp;&nbsp;&nbsp;&nbsp;(d) Brief description about what the user can/should do here (enter username and password to create new account...)\
&nbsp;&nbsp;&nbsp;&nbsp;(e) Save instance state
  
## Main_Activity
(i) The user can reach this via:\
&nbsp;&nbsp;&nbsp;&nbsp;(a) Login_Activity: by logging in; or by being logged in already when she opens/starts the app.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: nothing in particular, it seems (for requirements see Login_Activity).\
&nbsp;&nbsp;&nbsp;&nbsp;(b) Add_Saved_Charity (see below), by clicking a button there; or by using the Backpressbutton if she reached that Activity via Main_Activity. Briefly, in Add_Saved_Charity she can select charities she has saved earlier with probabilities and numerical values assigned to outcomes (via Save_New_Charity, see below) so that  these will be included in the calculation done here. If she has selected one or more charities, these will be shown on the page and will have to be included in the calculation.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: method to check if (if user comes from Add_Saved_Charity) user has added charities, via intent & if so, to add these to the calculation: make table with correct content; method to ensure that all charities that were selected or named earlier are saved (before going to Add_Saved_Charity) are restored.\
&nbsp;&nbsp;&nbsp;&nbsp;(c) Save_New_Charity (see below), by clicking a button there; or by using the backpressbutton if she reached that Activity via Main_Activity. Briefly, in Save_New_Charity, the user can select or insert a charity (like she can in this Activity), and assign probabilities and numerical values to outcomes (like in this Activity). These can then be accessed, but this isn't necessary, via Add_Saved_Charity, which can be accessed in this Activity.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: nothing in particular.\
&nbsp;&nbsp;&nbsp;&nbsp;(d) Calculate_Activity (see below), by using the Backpressbutton.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: nothing in particular, accept that everything has to be restored.\
(ii) The user can:\
&nbsp;&nbsp;&nbsp;&nbsp;(1) Click on an Icon to show information about what she can do on this page.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: Actionbar Icon; method for showing icon; method for handling events of Icon, showing if clicked a Dialog Fragment with text.\
&nbsp;&nbsp;&nbsp;&nbsp;(2) Click on a button with the text "Log out" to log out.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: Button for Logging out saying "Log out"; onClick function for button to log out from Firebase and redirect to Login_Activity.\
&nbsp;&nbsp;&nbsp;&nbsp;(3) Click on a button with the text "Add charity". She will see a scrollable list attached to the button:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: button; onClick for button to show listview, ideally not filling the entire screen, but starting immedately below the button, so that it's "attachted" to the button.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(3.1) The list contains names of charities  via an API: http://developer.everydayhero.com/charities/. She can click on one of the charities on the list.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: method with json object request (or something similar), an array adapter, which is attatched to the listview from (3). It's possible to click on the options.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(3.2) At the top of the list, there's an option "Name a charity." The user can also click on this.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: add to the listview from (3) above all the charities from (3.1) a static option, with the text "Name a charity."\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;If the user (3.1) selects a charity or (3.2) names a charity, we will see a table, with above it the name of the charity which has been selected, or if she has clicked "Name a charity" a box into which she can fill in the name of a charity herself. She cannot fill in the name of a charity she has already selected (for this particular calculation), and cannot fill in the name of a charity she already named. (The user also cannot select a charity with the same name as a charity she named herself). If she tries to do this, she will be notified. Both the selecting and naming can be done several times, but it seems useful to have a limit. (If there are more tables than can fit on the screen the user can scroll through them, but e.g. the buttons remain where they are).\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: method to make table if a charity is clicked in the listview; method to make a table if "Name a charity" with that name if that is clicked (or put this in the same method with an if statement or something). For the specific requirements of the table, relevant for these methods, see (4)-(8). The methods to create tables also have to implement some of the stuff mentioned in (4)-(8).\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The table has 4 rows and three columns. The three columns are:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(4) Outcome (left): (Above the top most table directly above this column is a text such as: "The charity achieves what it aims to do: "). The boxes from this column have texts like "Completely", "Almost", "Slightly", "Not at all." This content cannot be changed. The number of outcomes determines the number of rows.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: the create-table method(s) has to make a table with four rows and three columns. In the first column, from the top to bottom, it says in the top box "Completely", etc. The create-table method should remember or determine the number of tables already created. If it is the first, then above this table it should put the text ("The charity ..."). If not, it shouldn't add the text but should put this table below the ones already there, without overriding or altering the already existing tables.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(5) Probability (middle):(Above the top most table directly above this column is a text such as: "The probability that this outcome will come about is: "). The user has to 100% over all the outcomes, so assign a probability to the outcome coming about. The total should be a 100. There should be a user friendly, easy way to do this. Just typing in probabilities, and then changing them in other boxes because the total has to be a 100 is too annoying, so I'll have to think of some otherway. Don't know what this is yet.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: method (accessed by clicking on a box for probabilities or something) to easily divide 100% of probabilities over four boxes.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(6) Value (right): (Above the top most table directly above this column is a text such as: "The numerical value assigned to this outcome is: "). The user has to insert an integer. If she tries to do otherwise, she will be notified.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: method (accessed by clicking on a box for numerical values) to insert numerical values. If the user tries to insert something else or an integer which is too big, she will be notified.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(7) Each table has a button with the text "No clue!" If the user doesn't know what probabilities she should assign to the outcomes, she can opt to rely on the judgements of other users. Users can share the probabilities they assign to outcomes of particular charities via Firebase (see below, Save_New_Charity_Activity), and these can be combined and then used by other users. But, if the charity is named by the user, there is no data from other users, so the probabilities will be assigned equally to all outcomes.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: method to get data from Firebase.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(8) Each table has a button with the text "Remove" which deletes the charity from the calculation. All content will be lost. The user will be asked if she's sure before it actually happens.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: button; onClick to delete specific table and it's content.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(8) Clik a button with the text "Save new charity." The user will be directed to Save_New_Charity_Activity (see below).\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: button; onClick with intent to go to Save_New_Charity; method to save all tables and content, if there're any (same as (9)).\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(9) Click a button with the text "Add saved charity." The user will be directed to Add_Saved_Charity_Activity (see below).\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: button; onClick with intent to go to Add_Saved_Charity; method to save all tables and content, if there're any (same as (8) & (11)).\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(10) Click a button with the text "Show shared charities." The user is shown a list of all other charities, along with the calculations, shared by other users. (the user can save and share one herself via Save_New_Charity). They are sorted as follows: if a user has selected charities already, these are on top. Otherwise they're ordered alphabetically.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: method to get data from Firebase & show in proper way.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(11) Click a button with the text "Calculate!" as soon as there are two charities. All probabilities have to be properly assigned, and all outcomes have to have numerical values attatched to them. If something isn't done, the user will be notified what specifically is wrong. If all is well, she will be directed to Calculate_Activity. But, first the expected utility of all charities will be calculated, and the conclusions are given to an intent to go to Calculate_Activity.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: method to check if there are two or more tables (see also method for creating tables, which also has to keep track of the number of tables) & to show button if this is the case; method for button (or via the same method) with onClick to go to Calculate_Activity & to calculate for each table/charity the expected utility by using the assigned probabilities and numerical values & to put these conclusions in the intent for going to Calculate_Activity; method to store all tables and content so that they can be retrieved if the user returns (see also (8) & (9)).\
(iii) Also required:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(a) Check to see if the user is logged in via Firebase as soon as the user reaches this Activity. If not, she will be redirected to Login_Activity. If she is, nothing happens (so she remains on this page).\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: check auth state in onCreate; if statement in onStart to direct user to Login_Activity if not logged in.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(b) Nice Background\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(c) Title of page\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(d) Save instance state
  
## Calculate_Activity
(i) The user can reach this via:\
&nbsp;&nbsp;&nbsp;&nbsp;(a) Main_Activity: if the user pressed the button with the text "Calculate!" from Main_Activity, and some requirements were met, she's directed to this Activity.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: method which gets intent (created in Main_Activity, the onClick for calculate button) & gets data from intent (calculations of expected utility) & shows this in a table & determines which expected utility is greatest and shows this in a special place.\
(ii) The user can:\
&nbsp;&nbsp;&nbsp;&nbsp;(1) See what the expected utility of each charity is.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: see method above.\
&nbsp;&nbsp;&nbsp;&nbsp;(2) See what charity has the highest expected utility in a separate field.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: see method above.\
&nbsp;&nbsp;&nbsp;&nbsp;(3) If the charitiy with the highest expected utility was selected rather than inserted:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(3.1) There is a button she can click to direct herself to the website of the charity.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: method to determine if charity is selected or not; if it is: show button with text "Go to the website of "insert charity name here""; onClick function for button containing to website of charity.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(3.2) Click on a button with the text "Share calculations" to share the calculation & results with other users.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: method to share all calculations (charities, results) via Firebase with other users.\
&nbsp;&nbsp;&nbsp;&nbsp;(4) If the charity was named rather than selected, there is no button to share or to go to the website, but just the name of the charity.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: method to determine if the charity is named or selected (same as with (3)) & if named show a box with the text of the name.
&nbsp;&nbsp;&nbsp;&nbsp;(5) If several charities have the highest expected utility, all are shown. If they were selected, they can be forwarded to the website, and the expected utility can also be shared. Also, there's a button with the text "Recalculate." If the user clicks this, she will be directed to Main_Activity, and only the charities with previously the hihgest expected utility were shown, now with empty tables.\
&nbsp;&nbsp;&nbsp;&nbsp;(6) Click on a button with the text "Show other calculations" to see calculations shared by others via Firebase.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: button; onClick to show all calculations shared by other users (via (3.2)).\
&nbsp;&nbsp;&nbsp;&nbsp;(7) Click on a button with the text "Do another calculation" to go back to Main_Activity to start again.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: button; onClick with intent to go to Main_Activity.\
&nbsp;&nbsp;&nbsp;&nbsp;(8) Click on button with text "Log out" to log out.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: button; onClick containing: function to log out from Firebase & redirect to Login_Activity via intent.\
(iii) Also required:\
&nbsp;&nbsp;&nbsp;&nbsp;(a) Check if the user is logged in via Firebase. If not, she will be directed to Login_Activity. If she is, nothing happens (so she remains on the page).\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: check auth state in onCreate; if statement in onStart to direct user to Login_Activity if not logged in.\
&nbsp;&nbsp;&nbsp;&nbsp;(b) Nice background\
&nbsp;&nbsp;&nbsp;&nbsp;(c) Title of page\
&nbsp;&nbsp;&nbsp;&nbsp;(d) Brief text about what this page is for; or Information Icon.\
&nbsp;&nbsp;&nbsp;&nbsp;(e) Save instance state

## Add_Saved_Charity_Activity
(i) The user can reach this via:\
&nbsp;&nbsp;&nbsp;&nbsp;(a) Main_Acitivty: if the user clicked on the "Add saved charity" button.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: nothin in particular (see Main_Activity).\
(ii) The user can:\
&nbsp;&nbsp;&nbsp;&nbsp;(1) See a list of all saved charities (which have been saved via Save_New_Charity_Activity, see below)\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: listview linked to Firebase showing names of all charities saved by user, via Save_New_Charity_Activity.\
&nbsp;&nbsp;&nbsp;&nbsp;(2) Click on one ore more charities, thereby selecting them. This will be shown. If she clicks on them again, they will be unselected.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: eventhandler for listview: if clicked, saves this & shows this to user. If clicked again unsaves them & also shows this to user.\
&nbsp;&nbsp;&nbsp;&nbsp;(3) Click on one or more charities in another place, to show the table. If she clicks on that particular place again, the table will not be shown any more.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: another eventhandler for listview: if clicked show table: outcomes & probabilities & values assigned, retrieve via Firebase. If clicked again stop showing table.\
&nbsp;&nbsp;&nbsp;&nbsp;(3) Click on a button with the text "Add to calculation." The user will be directed to Main_Activity, and there the selected charities will are added to the calculation.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: method to detect which charities have been selected.\
&nbsp;&nbsp;&nbsp;&nbsp;(4) Click on a button with the text "Log out" to log out. The user is redirected to Login_Activity.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: button; onClick to log out from Firebase & intent to go to Login_Activity.\
&nbsp;&nbsp;&nbsp;&nbsp;(5) Click on an Icon showing what the user can do on this page.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: Actionbar Icon; method for showing icon; method for handling events of Icon, showing if clicked a Dialog Fragment with text.\
(iii) Also required:\
&nbsp;&nbsp;&nbsp;&nbsp;(a) Check to see if the user is logged in via Firebase. If not, she will be redirected to Login_Activity. If so, nothing happens (so she remains on this page).\
&nbsp;&nbsp;&nbsp;&nbsp;(b) Nice background\
&nbsp;&nbsp;&nbsp;&nbsp;(c) Title of page\
&nbsp;&nbsp;&nbsp;&nbsp;(d) Text showing what the page does; or Information icon.\
&nbsp;&nbsp;&nbsp;&nbsp;(e) Save instance state
  
  
## Save_New_Charity_Activity
(i) The user can reach this via:\
&nbsp;&nbsp;&nbsp;&nbsp;(a) Main_Activity: if the user clicked on the "Save new Charity" button.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: Nothing in particular, see Main_Activity.\
(ii) The user can:\
&nbsp;&nbsp;&nbsp;&nbsp;(1) Click on a button with the text "Save new charity." She can select or name a charity in the same manner as via Main_Activity (3). The probabilities and values have to be assigned, and the only significant difference is that there is no button with the text "No clue!" Also, it's not possible to give charities the same name as charities assigned earlier.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: button; onClick, but see Main_Activity; same function pretty much, make so that same method can be accessed in different Activities.\
&nbsp;&nbsp;&nbsp;&nbsp;(2) Click on a button with the text "Edit" to edit charities she saved earlier. She will see a list of all saved charities. The user can click on one, and she'll see the table. By clicking on the probabilities or values these can be changed.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: button; onClick so that all charities will be shown in listview & tables shown if clicked & such that values can be changed.\
&nbsp;&nbsp;&nbsp;&nbsp;(3) Click on a button with the text "Log out" to log out. The user will be directed to Login_Activity.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Required: button; onClick to log out from Firebase & intent to go to Login_Activity.\
(iii) Also required:\
&nbsp;&nbsp;&nbsp;&nbsp;(a) Check to see if the user is logged in via Firebase. If not, she wil be redirected to Login_Activty. If she's logged in, nothing happens (so she can stay on this page).\
&nbsp;&nbsp;&nbsp;&nbsp;(b) Nice background\
&nbsp;&nbsp;&nbsp;&nbsp;(c) Title of page\
&nbsp;&nbsp;&nbsp;&nbsp;(d) Text showing what this page does; or information Icon.\
&nbsp;&nbsp;&nbsp;&nbsp;(e) Save instance state

## APIs
http://developer.everydayhero.com/charities/
(If I can discover other APIs similar enough, it would be nice if I could try to combine the data from several APIs).

## Datasources
Firebase

(I don't know (and realized this a bit too late) what "utility modules" are. The methods are mentioned above, and it's explained in the text when they are used, and what they, roughly of course, do).
 
