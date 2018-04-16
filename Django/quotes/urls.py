from django.conf.urls import url
from . import views

app_name = 'quotes'

urlpatterns = [
	url(r'^$', views.index, name='index'),
	url(r'urlparts', views.examen, name='examen'),
	url(r'add/author', views.add_author, name='add_author'),
	url(r'add/form', views.add_form, name='add_form'),
	url(r'search/quotes', views.search_quotes, name='search_quotes'),
	url(r'search/form', views.search_form, name='search_form'),
	url(r'^(?P<author_name>[A-z ]+)/$', views.detail, name='detail'),
]
