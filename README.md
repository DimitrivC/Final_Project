# Final_Project: Calculating the Expected Utility to select a Charity
Dimitri van Capelleveen

### Summary
Via this app a user can determine which charity to choose if she's uncertain because its unclear what a charity will achieve. This will be done via the calculation of the expected value of choosing each selected charity (what expected value is will be properly explained below).

## Problem Statement
Suppose you have some money you want to donate to charity, and you're pretty certain about your values. For example, you might find it important that the lives of animals living on factory farms are improved. Unfortunately, it can be difficult to choose a charity because of empirical uncertainty: it can be unclear whether the relevant charities actually achieve what they promise to achieve with your donation. For example, you might find charities A and B attractive, of which A is unlikely to do good, but if it does good it's amazing, while B likely does good, but it achieves nothing special. It might be difficult to decide in such situations, but fortuntally this app provides the solution.

## What does the app do?
Roughly, the app determines which charity of the user's own choosing has the highest expected value (see Calculate) and should rationally speaking (according to expected utility theory) be chosen.

### Login
![alt text](https://github.com/DimitrivC/Final_Project/blob/master/doc/Login%20Final%20Project.png)

To use this app, a user has to be logged in via Firebase (so that the expected value calculations can be shared with other users). If the user is unaware of what the app does, she can click on the Information icon to get an explanation via a Fragment:
![alt text](https://github.com/DimitrivC/Final_Project/blob/master/doc/Info%20Frament%20Login%20Final%20Project.png)

If a user has no account yet, she can click on the "Create new account" button to create a new account. She will be forwarded to:

### Create new acount
![alt text](https://github.com/DimitrivC/Final_Project/blob/master/doc/Create%20New%20Account%20Final%20Project.png)

Here the user is able to create a new account. If succesful (or if she logs in via Login), she will be forwarded to:

### Main
![alt text](https://github.com/DimitrivC/Final_Project/blob/master/doc/Main%20Final%20Project.png)

MVP: The user create various charities by clicking on the button "Add charity." Charities can be selected via an API: http://developer.everydayhero.com/charities/  (such as some charity, other charity or another one in the picture) or she can add her own charities by adding the name herself. If a charity is selected, the user can get some info about it, also from an API.

MVP: For each charity there will be several outcomes that might come about if the user chooses that particular option. For example, the charity: completely achieves what is promised; almost achieves what is promished; ....; fails completely to achieve what is promised; makes matters worse.

MVP: For each outcome the user has to insert the probability of it happening if she donates to that particular charity. The total has to be a 100, and the user has to insert an integer. If a user exceeds 100 she's notified. It is shown how many percentage has to be diveded amongst the outcomes with no assigned probability. Suppose that the user has no idea, then the user can press the button "No clue!", in which case the percentages are divided equally amongst all options.

MVP: The user also has to a assign a numerical value to each outcome, which represents how bad or good she finds that outcome.

The user can also add to the calculation options she has saved. These can be selected by clicking on the button "Add saved charity" and created if the user clicks on the button "Save new charity" (see below for descriptions of both).

There's an info button showing brief descriptions of each of the buttons to ensure that the user knows how to use the app. There's a log out buttom to log out. The user will return to Login.

There's also a button to show the saved charities of others, "Show charities." You can share your charities, with numerical values and probabilities assigned to outcomes with other users. You will see this via a fragment. 

MVP: If the user presses the button "Calculate!":

### Calculate
![alt text](https://github.com/DimitrivC/Final_Project/blob/master/doc/Calculator%20Final%20Project.png)

For each charity it's calculated what the the expected value is. The probability of each outcome is multiplied with the numerical value assigned to that particular outcome, and all this is added together. Rationally speaking, the user should choose the charity with the highest expected value/utility. She can click a button to be forwarded to that website.


### Add saved charity
![alt text](https://github.com/DimitrivC/Final_Project/blob/master/doc/Add%20Saved%20Charities%20Final%20Project.png)

The user will see a list of all charties, together with probabilites assigned to outcomes as well as numerical values assigned to those outcomes, and can select one or more charities to add to the calculation. These charities with data are created via (which can be accessed via Main):

### Save new charity
![alt text](https://github.com/DimitrivC/Final_Project/blob/master/doc/Save%20new%20Charity%20Final%20Project.png)

The user can select a charity from the API mentioned above, or name one herself if it's not on the list. She can then select probabilities and numerical values. She can then save this for later use. 


## And:

### Hardest parts
Getting calculations from other users and sharing these; working and doing calculations with the numerically assigned numbers; making the insertiong of the probabilities as user friendly and easy to do as possible; intergrating information from different APIs (there are several of which you can get charties and other similar data, it would be nice to use data from several other than the example above); dealing in a good way with what happens when two or more charities get the same expected value; forwarding to the websites of charities.

### External proponents
Firebase; maybe some math library.

### Data sources
At least: http://developer.everydayhero.com/charities/

### Similar apps
There are several apps for choosing charities (e.g. Charity Navigator), but they don't seem to use expected value/utility calculations. Since their approach is different, I don't know if these are particularly relevant for me from a technical point of view. As far as I can tell, there are no apps which are specifically designed to determine the expected utility/value.
