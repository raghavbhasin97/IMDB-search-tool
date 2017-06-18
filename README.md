# IMDB Search Tool

Introduction
------------

A tool that let's you search the IMDB database for a movie, and get details for the closest match. This is a clean and simple to use tool, that works by scraping the IMDB website for details about the required movie. 
#### Sample use
![imdb sample run](https://user-images.githubusercontent.com/19142014/27253149-98839b20-538c-11e7-961e-0c9c763452a2.png)

Movitation
----------
IMDB doesn't have an official API that allows searching its database. Additionally, all the other tools availible are either paid or work only for javascript. I encountered these problems while I tried to make a movie search tool (with data from IMDB), and therefore, descided to make a web scrapper for IMDB instead.

About the API
-------------

The API at this moment is designed to only get data for a movie. It does so by scraping the web and finding the required data.

### Classes
It consists of two major classes with some public methods that provide acess to the functionality provided by this tool

#### IMDB Class
Represents an IMDB object that allows scraping the IMDB site in two ways

1). By The Movie name: Use the IMBD constructor with the movie name as frst parameter and true as the second paramter, this allows
the tool to search IMDB for closest matching mocie pages and set up the readers to actually start scraping the page.

2). By IMDB URL: If you have the URL of the page, you can use the IMDB constructor with first parameter as the URL and second paramter as false, to setup the reader to start extracting the page.

#### Movie Class
The Movie class represents a Movie object that stores the details about the movie you searched for. A movie has the following components:
  1). Title: The name of the movie
  2). Description: Plot summary for the movie
  3). Release Date: When the movie was released
  4). IMDB URL: IMDB address of the page corresponding to the movie
  5). Runtime: Duration of the movie in Minutes
  6). Director: Name of the director for the movie.
  7). Rating: How good the movie was on a scale of 1.0 to 10.0
  8). Genres: A list of all the genres that the movie falls under
  9). Actors: A list of people who were in the movie (some not all at times).
  10). Keywords: A list of keywords that match the movie search. 


Features in the API
-------------------
