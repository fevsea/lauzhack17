from django.shortcuts import render

# Create your views here.
from rest_framework import generics

from rest.models import Mission
from rest.serializers import MissionSerializer


class MissionList(generics.ListCreateAPIView):
    queryset = Mission.objects.all()

    serializer_class = MissionSerializer