from django.shortcuts import render

# Create your views here.
from rest_framework import generics
from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework.reverse import reverse

from rest.models import Mission
from rest.serializers import MissionSerializer, MissionSerializerDetail


class MissionList(generics.ListCreateAPIView):
    queryset = Mission.objects.all()

    serializer_class = MissionSerializer

class MissionDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = Mission.objects.all()
    serializer_class = MissionSerializer

    def get_serializer_class(self):
        if self.kwargs['pk'] is None:
            return MissionSerializer
        return MissionSerializerDetail

@api_view(['GET'])
def api_root(request, format=None):
    return Response({
        'missions': reverse('missions_list', request=request, format=format),
        'coreAPI schema': reverse('schema', request=request, format=format),
    })