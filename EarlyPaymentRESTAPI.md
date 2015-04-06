# Introduction #

REST calls to get early payment data.


# Details #

**Get EP available Count:** http://72.167.4.179/taulia_android_app/index.php?qry=ep_count

Response:
```
<response>
	<method>ep_count</method>
	<ep_count>2</ep_count>
</response>
```

**Get EP List:** http://72.167.4.179/taulia_android_app/index.php?qry=ep_list

Response:
```
<response>
	<method>ep_list</method>
	<eps_available>
		<ep>
			<invoice_id>00000000000000000001</invoice_id>
			<invoice_num>Emeka1001</invoice_num>
			<amount>3450.23</amount>
			<duedate></duedate>
			<buyer>Coke</buyer>
		</ep>
		<ep>
			<invoice_id>00000000000000000002</invoice_id>
			<invoice_num>Emeka1002</invoice_num>
			<amount>123.20</amount>
			<duedate></duedate>
			<buyer>Tim Hortons</buyer>
		</ep>
	</eps_available>
</response>
```

**Get Specific EP Offer:** http://72.167.4.179/taulia_android_app/index.php?qry=ep_get&inv_id=1

Response:
```
<response>
	<method>ep_get</method>
	<ep>
		<ep_status>new</ep_status>
		<invoice_id>00000000000000000001</invoice_id>
		<invoice_num>Emeka1001</invoice_num>
		<amount>3450.23</amount>
		<currency>USD</currency>
		<apr>2</apr>
		<duedate>2012-11-28 11:35:36</duedate>
		<invoicedate>2012-09-26 13:52:36</invoicedate>
		<buyer>Coke</buyer>
	</ep>
</response>
```

**EP Submit:** 72.167.4.179/taulia\_android\_app/index.php?qry=ep\_submit&inv\_id=00000000000000000002&ep\_date=2012-10-10

Response:
```
<response>
	<method>ep_submit</method>
	<ep_submit>
		<success>true</success>
		<invoice_id>00000000000000000002</invoice_id>
		<payment_date></payment_date>
	</ep_submit>
</response>
```