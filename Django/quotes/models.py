from django.db import models

class Author(models.Model):
	author_name = models.CharField(max_length=50)
	author_bio = models.CharField(max_length=500)

	def __unicode__(self):
		return self.author_name

class Quote(models.Model):
	quote_text = models.CharField(max_length=300)
	quote_author = models.ForeignKey(Author)

	def __unicode__(self):
		return self.quote_text

	def search_quote(self, word):
		return word in self.quote_text
		
class TEST(models.Model):
	test_name = models.CharField(max_length=50)
	test_bio = models.CharField(max_length=500)

	def __unicode__(self):
		return self.test_name
