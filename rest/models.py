from django.db import models

class Mission(models.Model):
    name = models.CharField(max_length=100)
    description = models.CharField(max_length=500)
    url = models.CharField(max_length=150)
    img = models.CharField(max_length=150)

    def __str__(self):
        return self.name

