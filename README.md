# about_your_book_search


 - Install: 
 
   - Clone this project in your home;
   - To install, navigate to the project folder and run: lein install;
   - After that, run: lein run to start the API;
   - The API is running on localhost:3000;

 - To Louch:
 
   - Open your browser and enter the URL: localhost:3000 to access the API;
   - This API has only one endpoint POST "/api/search-your-book", and in your payload you type the name of the book you want        to get information about.
   - After the POST, you will receive a "map" containing various information about the researched book, such as: author, date      of publication, country of publication, description, etc.
