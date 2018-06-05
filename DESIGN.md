Title

Briefly about the content of the app.

Picture

About the format of the description: (i), (ii), (iii), link to picure.

## Login_Activity
(i) The user can reach this via:
  (a) Starting the app. This is the first Activity.
    Required:
  (b) By logging out via Main_Activity; Calculate_Activity; Add_Saved_Charity_Activity; Save_New_Charity_Activity (so, all other activities with the exeption of Create_New_Account_Activity).
    Required:
  (c) By pressing backpressbutton via Main_Activity. (but, this should be overrided: the user should then leave the app?).
    Required:
  (d) By pressing bakcpressbutton via Main_Activity.
    Required:
(ii) The user can:
  (1) Click on the information Icon. If she does, she will see a @Fragment, with text explaining the app.
    Required: Icon (xml); onClick function for Icon (xml); onClick function for Icon (java) containing: stuff to show @Fragment; text in Fragment
  (2) Click on the Creat New Account button if she has no account yet or wants to make another one. She will be forwarded to Create_New_Account_Activity (see below).
    Required: Button with text "Create New Account"; onClick function for if user clicks it?; intent to go to Create_New_Account_Activity
  (3) Log in, via Firebase. She can log in via a username and password. If successful, she will be forwarded to Main_Activity. If not, she will be notified why not.
    Required: connection to Firebase; possibility to enter password; possibility to enter username; button to Login with text "Login";       onClick for button; check for if successful; if successful intent to go to Main_Activity; if unsuccessful show text in relevant box.
(iii) Also required:
  (a) Check if the user is logged in via Firebase as soon as she reaches this Activity. If not, nothing happens (meaning she remains here). If she is, she will be redirected to Main_Activity. (or to the Activity she was when she Logged out?)
    Required: connection to Firebase.
  (b) Nice background
    Required:
  (c) Nice picture which is somehow relevant to both calculating EU & Charities.
    Required:
  (d) Title of app
    Required:
  (e) Very brief description of app directly below title.
    Required:  
  
## Create_New_Account_Activity
(i) The user can reach this Activity via:
  (a) Login_Activity: by clicking the button "Create New Account."
  (b) Main_Activity: by clicking the backpressbutton if the user had created an account and then went to Main_Activity.
(ii) The user can:
  (1) Enter a username suitable for Firebase.
    Required:
  (2) Enter a password suitable for Firebase.
    Required:
  (3) Enter that same password again
    Required:
  (4) Click on a button to create a New Account. If successful, she will be forwarded to Main_Activity. If unsuccessful, show reason,
    Required:
(iii) Also required:
  (a) Check to see if the user is logged in via Firebase as soon as she reaches this Activity. If not, she will be redirected to Login_Activity. If she is, nothing happens (so that she can stay on this page).
    Required: Conntection to Firebase (java); 
  (b) Nice background
    Required:
  (c) Title of page "Create new account"
    Required:
  (d) Brief description about what the user can/should do here (enter username and password to create new account...)
    Required:
  
  
## Main_Activity
(i) The user can reach this via:
    (a) Login_Activity: by logging in; or by being logged in already when she opens/starts the app.
      Required: nothing in particular, it seems (for requirements see Login_Activity).
    (b) Add_Saved_Charity (see below), by clicking a button there; or by using the Backpressbutton if she reached that Activity via Main_Activity. Briefly, in Add_Saved_Charity she can select charities she has saved earlier with probabilities and numerical values assigned to outcomes (via Save_New_Charity, see below) so that  these will be included in the calculation done here. If she has selected one or more charities, these will be shown on the page and will have to be included in the calculation.
      Required: if she has selected one or more charities: this has to be noticed, via intent?; these charities will have to be added just like the ones selected here.
    (c) Save_New_Charity (see below), by clicking a button there; or by using the backpressbutton if she reached that Activity via Main_Activity. Briefly, in Save_New_Charity, the user can select or insert a charity (like she can in this Activity), and assign probabilities and numerical values to outcomes (like in this Activity). These can then be accessed, but this isn't necessary, via Add_Saved_Charity, which can be accessed in this Activity.
    (d) Calculate_Activity (see below), by using the Backpressbutton.
(ii) The user can:
  (1) Click on an Icon to show information about what she can do on this page.
    Required: Icon; onClick function for Icon; Fragment linked to Icon; text in Icon.
  (2) Click on a button with the text "Log out" to log out.
    Required: Button for Logging out saying "Log out" (xml file); onClick function added to button (xml); onClick function for button (java) containing: Firebase thing for loggin out; intent to go to Login_Activity; finish.
  (3) Click on a button with the text "Add charity". She will see a scrollable list attached to the button:
    Required: button; onClick; scrolable list
    (3.1) On the list If the user clicks on the "Add charity" button, she will see a list, attached to the button, which she can scroll down. The list contains names of charities retrieved via the API: http://developer.everydayhero.com/charities/
        Required: list should be filled with API content, dynamic; options should be clickable.
    (3.2) Name a charity. At the top of the list, there's an option "Insert charity." If the users clicks this, there will be a table, with no name:
      If the user (3.1) selects a charity or (3.2) names a charity, we will see a table, with above it the name of the charity which has been selected, or @@@ name inserted isn't there yet.
      The table has X rows and three columns. The three columns are:
      Outcome (left): (Above the top most table directly above this column is the text: ). This content cannot be changed. The number of outcomes determines the number of rows.
        Required:
      Probability (middle):
        Required:
      Value (right):
        Required:
      Both the selecting and inserting can be done multiple times, but it seems that it's useful to have a limit to the amount that can be selected/named.
      
  (6) Clik a button:
  Calculate button. If she hasn't done what was required, she will be notified. Specifically: @@.
  Save new Charity
  Add Saved charity.
    Required:
(iii) Also required:
  (a) Check to see if the user is logged in via Firebase as soon as the user reaches this Activity. If not, she will be redirected to Login_Activity. If she is, nothing happens (so she remains on this page).
    Required:
  (b) Nice Background
    Required:
  (c) Title of page
    Required:
  
## Calculate_Activity
(i) The user can reach this via:
  (a) Main_Activity: if the user pressed the button with the text "Calculate!" from Main_Activity, and some requirements were met, she's directed to this Activity.
  Required: the data with the calculations is retrieved from the intent created in Main_Acitivty in the onClick from the button with the text "Calculate!" from Main_Activity.
(ii) The user can:
  (1) See what the expected utility of each charity is.
      Required: data from the intent from Main_Activity is taken and put in special table; table.
  (2) See what charity has the highest expected utility in a separate field.
      Required: box for that content; data has to be taken from intent from Main_Activity.
  (3) If the charitiy with the highest expected utility was selected rather than inserted, there is a button she can click to direct herself to the website of the charity.
      Required: button with text "Go to the website of "insert charity name here""(xml); onClick function added to button (xml); onClick  function for button (java) containing: method for going to website of charity; finishig this activity? No, don't think so.
  (4) Click a button to do the calculations again.
  (5) Click on button with text "Log out" to log out.
    Required: button with text "Log out" (xml); onClick for that button (xml); onClick for that button (java) containing: function to log out from Firebase & redirect to Login_Activity via intent.
(iii) Also required:
  (a) Check if the user is logged in via Firebase. If not, she will be directed to Login_Activity. If she is, nothing happens (so she remains on the page).
    Required: Firebase access;
  (b) Nice background
    Required:
  (c) Title of page
    Required:
  (d) Brief text about what this page is for; or Information Icon.
    Required:

## Add_Saved_Charity_Activity
(i) The user can reach this via:
  (a) Main_Acitivty: if the user clicked on the "Add saved charity" button.
    Required: nothin in particular (see Main_Activity).
(ii) The user can:
  (1) See a list of all saved charities (which have been saved via Save_New_Charity_Activity, see below)
    Required:
  (2) Select one or more charities.
    Required:
  (3) Click on a button to go back to Main_Activity so the selected charities are added to the list. 
    Required:
  (4) Log out.
    Required:
(iii) Also required:
  (a) Check to see if the user is logged in via Firebase. If not, she will be redirected to Login_Activity. If so, nothing happens (so she remains on this page).
  (b) Nice background
  (c) Title of page
  (d) Text showing what the page does; or Information icon.

## Save_New_Charity_Activity
(i) The user can reach this via:
  (a) Main_Activity: if the user clicked on the "Save new Charity" button.
    Required: Nothing in particular, see Main_Activity.
(ii) The user can:
(1) Select a charity
  Required:
(2) Insert a charity
  Reequired:
(3) If she has done (2) or (3), she can, for that particular charity
  Required
(4) Edit charities saved earlier.
  Required:
(5) Log out.
  Required:
(iii) Also required:
  (a) Check to see if the user is logged in via Firebase. If not, she wil be redirected to Login_Activty. If she's logged in, nothing happens (so she can stay on this page).
  (b) Nice background
  (c) Title of page
  (d) Text showing what this page does; or information Icon.
  
