# Revature Project 1: 
    PI Name: Tocomo
    GroupID: com.github.vazidev.tocomo

## Cross Border Money Transfer Application
    An application that can be used to enable money Transfer across multiple currencies, by retrieving currency conversion rates, and tracking transactional records.
    Not intended to maintain persistence on individual Account Balances.




##Cassandra Settings:
Data Persistence maintained by aassandra DB.

 Cassandara KeySpace:
 
   1.  "tocomo"

  Databases Utilized:
  
    1.  Customers:  User Accounts Database.
    2.  Transactions: Transactional Data, across user Accounts.

##Features

The following features will be added to the Api.

HTML and Terminal based Services.
  
    1.  Accounts Registration
    2.  Transactional GUI.
    3.  Accounts Review
    4.  Transactions Review
    5.  Accounts deletion
    6.  Accounts Update
    


## Technologies Utilized
The following technologies are utilized withiin the API.

    i.    JAVA.
    ii.   Junit
    iii.  Logback SLF4j
    iv.   Cassandra DB
    v.    Maven
    vi.   DataStax
    vii.  Spring Framework
    viii. Github



##DataBase : REST Endpoints.

###tocomo.customers:

    i.    GET / : Web Based GUI
    ii.   GET /cust: Retrieves account information from the transactions database, utilizing "FLUX< >"
    ii.   GET /cust/{param} :  Singular Transactions Retrieval utilizing MONO< > 
    iii.  POST /cust/new : New Accounts Registration
  
 ### tocomo.transactions:
    i.    GET / : index.html : Web based GUI
    ii.   GET /trx: Retrieves account information from the transactions database, utilizing "FLUX< >" 
    ii.   GET /cust/{param} :  Singular Transactions Retrieval utilizing MONO< > 
    iii.  POST /cust/new : New Accounts Registration
  
  
  ### Currents Status:
    1. Unable to connect Spring Query functionality to cassandra DB, by initiating the Reactive Server
