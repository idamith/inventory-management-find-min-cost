# Problem: Inventory Management

<p>Recently a new online store opened on the Internet that sells iPods & iPhones. They are faced with an interesting problem of managing their inventory distributed across 2 countries: Brazil and Argentina. You have been assigned to write a program to minimize the sale price for online customers based on its inventory in these 2 countries.</p>
<p>The price are different based on the country where the inventory is stored. The inventory in the two countries is limited. There are 100 iPods in Brazil which have a sale price of $65 whereas there are 100 in Argentina which have a sale price of $100. There are 100 iPhone in Brazil which have a sale price of $100 whereas there are 50 in Argentina which have a sale price of $150. For simplicity we will use US dollars as our transaction currency.</p>
<p>The order should be fulfilled fully or not at all. If the inventory from one country in used up item have to be fetched from the other country. There is a transport cost involved when the item needs to be shipped from one country to another in case the purchase country is different than the inventory country. Shipping cost is $400 for every 10 units of item type (no mixing of item types). Note that transport cost is always in multiples of 10 units. If customer passport belongs to local country then the customer will get 20% discount on
the transport cost. For example a customer placing order from Argentina and has a passport of Brazil, while shipping order from Brazil the transport cost will be charged ($400 - 20% = $320) for every 10 units. To identify passport from brazil, passport number starts with B, followed by 3 digits, followed 2 chars, followed by 7 alphanumeric characters. And for Argentina, passport number starts with A, followed by 2 chars, followed by 9 alphanumeric characters.</p>
<p>To minimize the total sales price it can be partly fulfilled from one country and remaining from other. Assume that before each purchase order the inventory is replenished to its normal level. Thus the orders are independent of each other.</p>
 
<p>Use following input/output to understand the problem better. If there is something which is not clear, do not hesitate to post your questions on AgileDevBootcamp forum. Also if you find any discrepancies in the problem or sample input/output please let us know.</p>

<b>INPUTFORMAT:</b> (nospacebetweenseparators) <purchase_country><b>:<optional_passport_number>:</b><item_type>:<number_of_ units_to_be_ordered>:<item_type>:<number_of_units_to_be_ordered> 

<b>OUTPUT FORMAT:</b> <total_sale_price>:<ipod_brazil_inventory>:<ipod_argentina_inventory> :<iphone_brazil_inventory>:<iphone_argentina_inventory>

<b>INPUT 1:</b>
BRAZIL:B123AB1234567:IPHONE:20:IPOD:10
OUTPUT 1:
2650:90:100:80:50

<b>INPUT 2:</b>
ARGENTINA:B123AB1234567:IPHONE:22:IPOD:10
<b>OUTPUT 2:</b>
3910:90:100:80:48

<b>INPUT 3:</b>
BRAZIL:AAB123456789:IPHONE:125:IPOD:70
<b>OUTPUT 3:</b>
19260:30:100:0:25

<b>INPUT 4:</b>
ARGENTINA:AAB123456789:IPOD:50:IPHONE:25
<b>OUTPUT 4:</b>
8550:100:50:80:45

<b>INPUT 5:</b>
BRAZIL:IPHONE:50:IPOD:150
<b>OUTPUT 5:</b>
18500:0:50:50:50

<b>INPUT 6:</b>
BRAZIL:IPHONE:250:IPOD:150
<b>OUTPUT 6:</b>
OUT_OF_STOCK:100:100:100:50
