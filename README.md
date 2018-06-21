# URLMapper
A URL Mapper is used to map long and frequent URLs to smaller addresses.

# Database:
MySQL

# How to run this application?
For running this application: 
You need:

1.) Java JDK(latest version)
2.) Maven(latest version)

First set up the database running and all configuration data is in the application properties file.

Then clone this project and run the Application class
Go to 
# 1.)For Adding Mapping 
localhost:8080/go/AddMapping/Key/URL/Protocol
localhost:8080/go/AddMapping/google/www.google.com/https/

# 2.)For Deleting Mapping
localhost:8080/go/DeleteMapping/Key
localhost:8080/go/DeleteMapping/google

# 3.)For Overwrite Mapping
localhost:8080/go/OverwriteMapping/Key/URL/Protocol

# 4.)For Listing all Existing Mappings
localhost:8080/go/ListAllMapping

# 5.)For Using shortened link
localhost:8080/go/redirect/Key



