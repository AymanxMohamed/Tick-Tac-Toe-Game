
<h1 align="center"> Tick-Tac-Toe-Game </h1>

<p align="center">
   <img src="https://github.com/AymanxMohamed/Tick-Tac-Toe-Game/blob/main/00%20Project%20Materials/05%20Images/logo.jpg" alt="Build Status" width="300">
</p>

## Description:
 
A NetWork based Tic-Tac-Toe application that consists mainly of two applications `server` and `client`. 
The Game have a nice  looking user interaface.
The Application done mainliy using `javafx` the server has `MS-SQL-SERVER` database that stores all  the information about the players. and The games they are played. The application designed using the  `MVC` pattern. The communication between the server and the client in this game done using `json objects` to make it easy to send and recieve requests and responses.
## main features

- Nice looking User Interface

- Register to the game

- server side validation

- play with `ai computer`

- updated list with full information about the registered players

- The ability to `Chat` with any player in the list

- The ability to `play` with any player in the list

- Updated list of the whole single and multi mode game history

- The ability to replay any previous game from the `history` list

- in Server Application you have the same players  list with the ability to `toggle` server tatus
### Application Features details

- Sign up and Sign in as a first time player

- Every player have a bonus points he gain after wining any game or lose if when he lose the game
- players are classifed according to the bonus points to several ranks
`bronze`, `silver`, `gold`, `platinum`, `Diamond`, `Master`, `Grand Master`,`Challenger`
- the way that the players gain and lose the points differ in each rank.

- player can play in `single mode game` with `ai`

- Every player will have an updated list of the whole players in the server database 
this with the information of their `name`, `bonus points`, `rank`, `status[online, offline]`, `in Game or not`, `in chat or not `

- The player can click on any player in the list and `play` or `chat`  with him
only if this player is `online` and if the player is not in `game` or in `chat`.

- The server handle all most of the possible senarios that can happen in the appication during the  game or because of any issue.

- The player can replay any game from the history.

## Designs

### Database ERD
<p align="center">
       <img src="https://github.com/AymanxMohamed/Tick-Tac-Toe-Game/blob/main/00%20Project%20Materials/00%20Database/00%20ERD/TicTacToeERD-Final.drawio.svg" alt="Build Status">
</p>

### Application Design
 - in this game the client is actualy nothing 
 - The `Server` is the big boss her :)
#### Client Application

The client application consists mainly of 4 packages

- `controllers`: This pacakage is Responsible for dealing with the game views

- `models`: This package is for storing the data of the entities that send from the server
    - `player`
    - `singleModeHistory`
    - `multiModeHistory`

- `netwok`:` This package is the responsable for every thing it has the classes That responsable for dealiing with the server 
    - `Client` this object  is the responsable for open a socket with the server 
        - it has an `acceptResponse` method  that  have a `runnable` object that keep reading the buffer to accept  a response from t he server
        - once it read a  request it pass it to the next object to handle it  `ResponseHandler`
        - The `client` also has a `sendRequest` function that sends  a request to the server as a json string so the server can parse it and send a response after it.
    - `ResponseHandler` This object has a function `handleResponse` that takes a response from the server and do a certin action according to this response.
    - `RequestCreator` this is a utility class that is responsable for creating the json objects and return a string to be send to the server.

#### Server Application

The big boss in this  app The server is the responsable for every thing
- validating player  data
- connecting with the database 
- stablish the conection between players
- reciving the message from player and send it to the other one etc...

The  server has mainly 4 packages 

- `database` This package is the responsable for dealing with the database and it has mainly utility class `DatabaseManager` that do every thing
    - `DatabaseManager` this class is the responsable for managing any `CRUD` operatin throw the application life cycle
- `network` same as the client this package it the responsable for dealing with all the clients in connected to the server. it has mainly 4 objects to do this.
    - Server this calss is the responsable for  accepting new clients socket then passing it to the next object `playerHandler`
    - `playerHandler` each client has a playerHandler for him to acceptReqeust and send a response from him this object has 2 main methods to do thi
        - `AcceptReqeust` it's the opposite of the `AcceptRespoonse` of the client
        this method takes a request as a json from the client and pass it to the next Object `RequestHandler` to handle this request and return immediatelty a certain `response` to send it back to the client.
    - `RequestHandler` This object is a class that have handleRequest method that takes the user request and sends an approbriate  response to the `player` with the help of the next object.
    - `ResponseCreator` it's a utility class that create a `response` as a `json object` to be send to the `player`

    
- `gameLogic` this package has 3 classes that mainly control the game and chat process
    - `singleModeGameHandler`
    - `multiModeGameHandler`
    - `chatRoomHandler`

## IN Game Process

### In Server Side

    Server must be oppendd first because without oppening the server the client won't be able to go to his login screen and he will get an error 
   
<p align="center">
    <img src="https://github.com/AymanxMohamed/Tick-Tac-Toe-Game/blob/main/00%20Project%20Materials/05%20Images/serverViews/00.jpg" alt="Build Status">
</p>

    After opening the server you will have the whole players list with the status of each player
   
<p align="center">
    <img src="https://github.com/AymanxMohamed/Tick-Tac-Toe-Game/blob/main/00%20Project%20Materials/05%20Images/serverViews/01.jpg" alt="Build Status">
</p>

### Login & Register process

#### indexView
this is the first view in the client app and it won't go to the `login` view if the server isn's oppend if the `player` tried to login when server is down he will get an `error message`
<p align="center">
       <img src="https://github.com/AymanxMohamed/Tick-Tac-Toe-Game/blob/main/00%20Project%20Materials/05%20Images/00%20Client/login/00.jpg" alt="Build Status">
</p>

<p align="center">
       <img src="https://github.com/AymanxMohamed/Tick-Tac-Toe-Game/blob/main/00%20Project%20Materials/05%20Images/00%20Client/login/01.jpg" alt="Build Status">
</p>



#### register
    if the player is new to the game he can register first  

<p align="center">
    <img src="https://github.com/AymanxMohamed/Tick-Tac-Toe-Game/blob/main/00%20Project%20Materials/05%20Images/00%20Client/register/05.jpg" alt="Build Status">
</p>

    if the password dosn't match he will got an error

<p align="center">
    <img src="https://github.com/AymanxMohamed/Tick-Tac-Toe-Game/blob/main/00%20Project%20Materials/05%20Images/00%20Client/register/06.jpg" alt="Build Status">
</p>

#### login

    If the server is opened so the player will  go to the authontication stage
    

<p align="center">
       <img src="https://github.com/AymanxMohamed/Tick-Tac-Toe-Game/blob/main/00%20Project%20Materials/05%20Images/00%20Client/login/02.jpg" alt="Build Status">
</p>

    if the player name is wrong he will get an error from server

<p align="center">
       <img src="https://github.com/AymanxMohamed/Tick-Tac-Toe-Game/blob/main/00%20Project%20Materials/05%20Images/00%20Client/login/03.jpg" alt="Build Status">
</p>

    and if the password is wrong he will also get an error

<p align="center">
       <img src="https://github.com/AymanxMohamed/Tick-Tac-Toe-Game/blob/main/00%20Project%20Materials/05%20Images/00%20Client/login/04.jpg" alt="Build Status">
</p>


    after the player login successfuly he will get the welcome screen and informed
    with the information with his current rank and bonus points
<p align="center">
       <img src="https://github.com/AymanxMohamed/Tick-Tac-Toe-Game/blob/main/00%20Project%20Materials/05%20Images/00%20Client/After%20Login/06.jpg" alt="Build Status">
</p>

    player can play with the computer and specify the difficulty
   
   

https://user-images.githubusercontent.com/72627215/154819657-1707f18c-8bc8-4832-ae6a-d0b96940f892.mp4




    player can navigate to the online home view to see the players list and if their
    is player online he can send a private chat invitation or game invitation to him

<p align="center">
       <img src="https://github.com/AymanxMohamed/Tick-Tac-Toe-Game/blob/main/00%20Project%20Materials/05%20Images/00%20Client/After%20Login/07.jpg" alt="Build Status">
</p>

    send chat invitation process

https://user-images.githubusercontent.com/72627215/154819356-b5f4c291-c308-4e3c-b691-4b417e711ec1.mp4
   
    send game invitation process
   
   

https://user-images.githubusercontent.com/72627215/154819699-19a22273-abfc-40f0-a9c8-e325bd89a9c8.mp4





    player also can navigate to his histry to start see his previous game with 
    the ability to click on any game and play it



https://user-images.githubusercontent.com/72627215/154819722-d28422cd-a888-4a81-b37e-f70c306c72e1.mp4





<p align="center">
       <img src="https://github.com/AymanxMohamed/Tick-Tac-Toe-Game/blob/main/00%20Project%20Materials/05%20Images/00%20Client/After%20Login/08.jpg" alt="Build Status">
</p>

<p align="center">
       <img src="https://github.com/AymanxMohamed/Tick-Tac-Toe-Game/blob/main/00%20Project%20Materials/05%20Images/00%20Client/After%20Login/09.jpg" alt="Build Status">
</p>

## How to play

- First Clone The Project
- Create the database schema 
  - you have to use Microsoft sql server
  - go to the /'00 Project Materials'/'00 Database'/'02 Database quires' and open 'new Database Schema.sql' file in your database management studio
  - after creating the data base you have to add user to the database
    - username: ayman
    - password: @01208538504@
    - databaename: TicTacToe
    - or simbly create new database but in this case you have to change the configuration in DatabaseManager.openDatabaseConnection Method
<p align="center">
       <img src="https://github.com/AymanxMohamed/Tick-Tac-Toe-Game/blob/main/00%20Project%20Materials/05%20Images/serverViews/03.jpg" alt="Build Status">
</p>


  - After creating the database you can now build and run the server project using netbeans or intellij idea as a maven project
  - you can lunch the Client App using the executable jar file or using netbeans or intellij idea
  - You can download the jar file from this link: https://drive.google.com/drive/folders/1kxtF2Oac1KPSkNlT367PQZKnHkjNJ6Zg?usp=sharing
  - if you have any problems in running the game feel free to contact us
  - note that the server is runing by default on port 9000  you can change this in the Server class
  - note: you will never be able to try the game without the server you have to open the server first 
## Contributors

<table>
  <tr>
    <td>
      <img src="https://avatars.githubusercontent.com/u/72627215?v=4"> </img>
    </td>
    <td>
    </td>
    <td>
      <img src="https://avatars.githubusercontent.com/u/58668061?v=4"></img>
    </td>
  </tr>
  <tr>
    <td>
      <a href="https://github.com/AymanxMohamed"> Ayman kheirEldeen </a>
    </td>
   <td>
   </td>
    <td>
      <a href="https://github.com/ahmedelshopaky"> Ahmed ElShopaky </a>
    </td>
  </tr>
   <tr>
    <td>
      <img src="https://avatars.githubusercontent.com/u/72516521?v=4"></img>
    </td>
    <td>
      <img src="https://avatars.githubusercontent.com/u/97949259?v=4"></img>
    </td>
    <td>
      <img src="https://avatars.githubusercontent.com/u/97365136?v=4"></img>
    </td>
  </tr>
  <tr>
    <td>
      <a href="https://github.com/reemadelsamak"> Reem Adel </a>
    </td>
      <td>
      <a href="https://github.com/dina810"> Dina Reda </a>
    </td>
     <td>
      <a href="https://github.com/MaiiEmad"> Mai Emad </a>
    </td>
  </tr>
</table>
