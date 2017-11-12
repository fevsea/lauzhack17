from django.db import models

class Mission(models.Model):
    name = models.CharField(max_length=100)
    description = models.CharField(max_length=500)
    url = models.CharField(max_length=150)
    img = models.CharField(max_length=150)
    def __str__(self):
        return self.name

class DetailItem(models.Model):
    num = models.IntegerField()
    tag = models.CharField(max_length=50)
    value = models.TextField()
    mission = models.ForeignKey(Mission, related_name="web", on_delete=models.CASCADE)
