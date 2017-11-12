from django.contrib.auth.models import User
from rest_framework import serializers, models



from .models import Mission, DetailItem


class ItemSerializer(serializers.ModelSerializer):
    class Meta:
        model = DetailItem
        fields = ('num', 'tag', 'value')

class MissionSerializer(serializers.ModelSerializer):
    highlight = serializers.HyperlinkedIdentityField(view_name='mission_detail')
    web = ItemSerializer(many=True, read_only=True)
    class Meta:
        model = Mission
        #fields = ('pk', 'name', 'description', 'url', 'img', 'highlight')
        fields = ('pk', 'name', 'description', 'url', 'img', 'highlight', 'web')

class MissionSerializerDetail(serializers.ModelSerializer):
    web = ItemSerializer(many=True, read_only=True)
    class Meta:
        model = Mission
        fields = ('pk', 'name', 'description', 'url', 'img', 'web')
