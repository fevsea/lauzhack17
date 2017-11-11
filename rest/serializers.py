from django.contrib.auth.models import User
from rest_framework import serializers, models



from .models import Mission




class MissionSerializer(serializers.ModelSerializer):

    class Meta:
        model = Mission
        fields = ('name', 'description', 'img', 'url')