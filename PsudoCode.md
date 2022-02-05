# psudo Code

## Game Steps

> 1- Login View

- sign in

  - player will add his user name and password
  - you have to check for if this player exists in the database or not using boolean funcion `databaseManger.isValidPlayer(userName, password)`

    - if exist
      - you will call `databaseManager.getPlayer()`;
    - if not exist
      - send error message to the user that tell him that he entered an invalid user name and password

- sign up
  - player will add his userName and password then he will click on register
  - we will get call validateUserInput(); that will return true or false according to the user input
  - if false
    - ask the user to enter a valid information with error message
  - if true
    - use the DatabaseManger.addPlayer(); function to add the player to the data base then call DatabaseManger.getPlayer() to get the player object;
