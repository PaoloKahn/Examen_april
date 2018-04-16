from django.shortcuts import render

from .models import Author
from .models import Quote
import string
import re

def index(request):
	author_names = [a.author_name for a in Author.objects.all()]
	return render(request, 'quotes/index.html', {'author_names': author_names})

def detail(request, author_name):
    author = Author.objects.filter(author_name=author_name).first()
    quote_list = author.quote_set.all()

    return render(request, 'quotes/detail.html', {'quote_list': quote_list})

	

def examen(request):
	url = request.build_absolute_uri()
	print request.build_absolute_uri()
	values = url[38:]
	wordList = re.sub("/", " ",  values).split()
	print wordList
	return render(request, 'quotes/search_form.html', {})

def search_form(request):
    return render(request, 'quotes/search_form.html', {})

def add_form(request):
    return render(request, 'quotes/add_author.html', {})
	
def add_author(request):
	if request.method == 'POST': #kijken of er weldegelijk een post gedaan wordt
		#vars ophalen uit de post
		name = request.POST['name']
		bio = request.POST['bio']
		quotes = request.POST['quote'].split(";")
		#auteur aanmaken
		author = Author.objects.create(author_name=name, author_bio=bio)
		#al zijn quotes aanmaken
		for i in range(len(quotes)):
			quotez = Quote.objects.create(quote_text=quotes[i], quote_author=author)
			quotez.save()
		#saven
		author.save()
		#index terug renderen
		author_names = [a.author_name for a in Author.objects.all()]
		return render(request, 'quotes/index.html', {'author_names': author_names})

def search_quotes(request):
    if request.method == 'POST':
        word = request.POST['search_term']
        quote_list = Quote.objects.all()
        result = [q.quote_text for q in quote_list if q.search_quote(word)]

    return render(request, 'quotes/detail.html', {'quote_list': result})
