from django.conf.urls import url
from . import views

app_name = 'quotes'

urlpatterns = [
	url(r'urlparts', views.examen, name='examen')
]
