# Pizza Shop Challenge

#### Problem  
Finding Popular Build-Your-Own Pizzas
A pizza chain wants to know which topping combinations are most popular for Build Your Own pizzas.
 
Given the sample of orders that are attached, write an Android app to output the top # (By default is top 20, but should be able to modify this range as a setting in the app) most frequently ordered pizza configurations, listing the toppings for each along with the number of times that pizza configuration has been ordered. 
 
The user should also be able to create their own pizza configurations and store those custom pizza orders in a separate list. Using SQlite to handle those custom pizza orders for persistency. In addition, users should be able to favorite/unfavorite a custom order by selecting that order in the table. There should be some indicator to display whether a custom order has been favorited or not.

Use material design guidelines to create the UI. You are free to design your own layouts/animations/assets.
Your application should exhibit either the MVP or MVVM architecture. 
Use Dagger 2.0 to inject your dependencies.
Use retrofit
Use Android Data Binding Library to bind your layouts and view models.
Feel free to Use any means to interact with the SQLite database. You may use any 3rd party library to achieve the goal.
If your app requires to communicate between activities, fragments or other components, use Eventbus.
Feel free to contact us in case of any queries.


Please upload the project on a Github account, and include a README file explaining your implementation.
You should create separate branches from develop for every feature and finally merge them into master.

### Approach  
##### Architecture: 
MVP  
##### Libraries used:
Butterknife  (View Binding)  
Dagger  (Dependency Injection)  
Gson (JSON converter)
RecyclerView (Android Design)
CardView (Amdroid Design)

##### Steps
1. Read JSON data from file using GSON parser library.
2. Add unique toppings to a Hashmap as key and count as value.
3. Sort HashMap using a Collections.sort and a new Comparator.
4. Add sorted HashMap to a List to populate the Recycler View in PizzaList activity.
5. Create new activity for Custom orders
6. Initiaize model class with valid data from the activity.
7. Use Database helper class to Create and Insert values in database;
8. Create new activity for Order list.
9. Get all order data from database using Database helper methods and populate the recycler view.
10. Call Database Helper update method for updating values from OrderList activity.


#### Application
##### Activities
1. Splash Activity: To show Pizza Shop logo  
2. PizzaList Activity: Sorted list of top pizza orders, list length can be selected accordingly.
3. NewOrder Activity: Create new order by selecting multiple toppings. You can also save your order as favourite.
4. MyOrders Activity: List of orders made through the app.

##### Utility
1. DatabaseHelper: SQLiteOpenHelper class to manage databse and perform CRUD operations

##### Features
1. Sorted top pizza orders list, which can be changed from Overflow menu "Sort" item.
2. Create custom pizza order by entering your personal information and selecting multiple pizza topping. You can also mark your order to be favourite. (Database supported)
3. View your previous ordered history through "My Orders". You can change your favourite orders here. (Database supported)