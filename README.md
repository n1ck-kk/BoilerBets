# BoilerBets
Project Description:
  Our group is designing a web app that users can utilize to make sport bets, specifically on professional basketball. The main features of this web app include data and statistics on players and teams, and will provide the user with the option to place bets on different players, teams, and games. In order to organize the bets our users create, they will also have the ability to create and manage their own account. In order to organize and store our data, below are the tables we plan on including:
    User(userid, username, password, email, name)
    Team(teamid, teamName, location, coach, owner)
    Player(playerid, teamid, teamName, position, collegeName, playerNumber)
    PlayerStats(playerid, avgP, avgAST, avgBLK, avgSTL, avgTO, avgMin, avgFG, avgFG3, avgFT)
    TeamStats(teamId, wins, losses, winPct, avgP, avgFGP, avg3PP, avgFTP,  avgAST, avgTO, avgBLK)
    UserBets(userid, betid, amount, payout, betSuccess)
    BetStats(betid, teamid, startDate, endDate, odds, type)

Main Features:
 - A homepage for the user to sign in or, alternatively, create an account for new users
 - “Landing page” that features the users current bets, odds on their current bets, and the ability to make new bets
 - Statistics page that shows information for players and the teams they play for to allow users to make informed betting decisions

Example bets:
  Allows the user to search through the BetsStats table to choose what type of bet they want to create. After clicking on one, the page then prompts for an amount and redirects to the landing screen where the newly added bet is featured.

Tools:
 - React.JS
 - MySQL
 - Java
 - SpringBoot
 - Maven
