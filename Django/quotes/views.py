from django.shortcuts import render

from .models import Author
from .models import Quote
import string
import re

test = []
def examen(request):
	url = request.build_absolute_uri()
	print request.build_absolute_uri()
	values = url[30:]
	if "//" in values:
		return render(request, 'quotes/list.html', {'valuez' : test})
	wordList = re.sub("/", " ",  values).split()
	check = "//"
	print wordList
	if wordList:
	   test.append(wordList)
	return render(request, 'quotes/list.html', {'valuez' : test})
