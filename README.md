# JewelleryManagement
Jewellery Shop management project for reference purpose only

create tables in a mysql database with name 'jewel'

bill
customer
has4
item
pay
purchase
user

bill description

 Field         Type         Null  Key  Default  Extra         
 bill_no       int(11)      NO    PRI  NULL     auto_increment 
 rate_per_gms  varchar(20)  YES        NULL                    
 total_amount  varchar(20)  YES        NULL    

customer description

 Field   Type         Null  Key  Default  Extra          
 c_no    int(11)      NO    PRI  NULL     auto_increment 
 c_name  varchar(20)  YES        NULL                    
 c_addr  varchar(20)  YES        NULL                    
 c_phno  varchar(10)  YES        NULL                    

has4 description

 Field      Type     Null  Key  Default  Extra 
 item_code  int(11)  YES   MUL  NULL           
 bill_no    int(11)  NO    PRI  NULL           

item description

 Field      Type         Null  Key  Default  Extra          
 item_code  int(11)      NO    PRI  NULL     auto_increment 
 item_name  varchar(20)  YES        NULL                    
 category   varchar(20)  YES        NULL                    
 weight     varchar(20)  YES        NULL                    
 status     varchar(20)  YES        NULL                    

pay description

 Field    Type     Null  Key  Default  Extra 
 c_no     int(11)  YES   MUL  NULL           
 bill_no  int(11)  NO    PRI  NULL           

purchase description

 Field      Type     Null  Key  Default  Extra 
 c_no       int(11)  YES   MUL  NULL           
 item_code  int(11)  NO    PRI  NULL           

user description

 Field     Type         Null  Key  Default  Extra 
 u_name    varchar(20)  NO    PRI  NULL           
 password  varchar(20)  YES        NULL           
