import csv
import requests #requests the page to get
from BeautifulSoup import BeautifulSoup # HTML parsing library


url="http://www.showmeboone.com/sheriff/JailResidents/JailResidents.asp";
response= requests.get(url);
html=response.content;

soup=BeautifulSoup(html);


table = soup.find("tbody", attrs={"class": "stripe"})

if table is not None:
	list_of_rows=[]	
	for row in table.findAll("tr"):
		list_of_cells=[]
		for cell in row.findAll("td"):
			text= cell.text.replace("&nbsp;","")
			list_of_cells.append(text)
		list_of_rows.append(list_of_cells)

	print list_of_rows

	outfile=open("\inmates.csv","wb")
	writer= csv.writer(outfile)
	writer.writerow(["Last", "First", "Middle", "Gender", "Race", "Age", "City", "State"])
	writer.writerows(list_of_rows)
