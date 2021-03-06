import os
import sys
from xml.etree.ElementTree import Element, SubElement, Comment, tostring, ElementTree
from xml.etree import ElementTree
from xml.dom import minidom
import random
import time

"""
    Return a pretty-printed XML string for the Element.
"""
def prettify(data):
    rough_string = ElementTree.tostring(data, 'utf-8')
    reparsed = minidom.parseString(rough_string)
    return reparsed.toprettyxml(indent="    ")

# Returns the terrain type corresponding to the txt file
def getTerrain(content, mapWidth, mapHeight):
    terrain = {}
    for i in range(0, mapHeight):
        for j in range(0, mapWidth):
            char = content[i][j]
            if(char == "g"):
                terrain[i, j] = "grass"
            elif(char == "m"):
                terrain[i, j] = "mountain"
            elif(char == "s"):
                terrain[i, j] = "sea"
            else:
                print("WTF why didnt that work?")

    # print terrain[20][13]
    return terrain

def getItems(content, mapWidth, mapHeight):
    items = {}
    for i in range(0, mapHeight):
        for j in range(0, mapWidth):
            char = content[i][j]
            if(char == "t"):
                itemTypeString = "take-able"
                itemId = random.randint(0, 7)
                items[i, j] = {'type': itemTypeString, 'id': itemId}
            elif(char == "i"):
                itemTypeString = "interactive"
                itemId = random.randint(0, 2)
                items[i, j] = {'type': itemTypeString, 'id': itemId}
            elif(char == "n"):
                itemTypeString = "one-shot"
                itemId = random.randint(0, 7)
                items[i, j] =  {'type': itemTypeString, 'id': itemId}
            elif(char == "o"):
                itemTypeString = "obstacle"
                itemId = random.randint(0, 2)
                items[i,j] = {'type': itemTypeString, 'id': itemId}
            else:
                items[i,j] = {'type': -1, 'id': -1}

    # print terrain[20][13]
    return items

# Returns an integer representing the type of item (position in the enum)
def getItemType(numItemsToAdd, mapSize):

    # Generate a random number to determine if an item should be added here
    if (random.random() < (float(numItemsToAdd) / float(mapSize))):
        # Generate a random number between 0 and 3 to determine what type of item it is
        itemType = random.randint(0, 3)
        if(itemType == 0):
            # This is a takeable item. There are 8 types of takable items
            itemTypeString = "take-able"
            itemId = random.randint(0, 7)
            return {'type': itemTypeString, 'id': itemId}
        elif(itemType == 1):
            # This is a one-shot item. There are 8 types of takable items
            itemTypeString = "one-shot"
            itemId = random.randint(0, 7)
            return {'type': itemTypeString, 'id': itemId}
        elif(itemType == 2):
            # This is an interactive item. There are 3 types of takable items
            itemTypeString = "interactive"
            itemId = random.randint(0, 2)
            return {'type': itemTypeString, 'id': itemId}
        elif(itemType == 3):
            # This is an obstacle item. There are 3 types of takable items
            itemTypeString = "obstacle"
            itemId = random.randint(0, 2)
            return {'type': itemTypeString, 'id': itemId}

    return {'type': -1, 'id': -1}



# Returns the type of area effect
def getAreaEffectType(mapSize, numAreaEffectsToAdd):
    # Generate a random number (the probability of success is based on the numAreaEffectsToAdd and mapSize)
    if(random.random() < (float(numAreaEffectsToAdd) / float(mapSize))):
        i = random.randint(0, 4)
        if(i==0):
            return "level-up"
        elif(i==1):
            return "heal-damage"
        elif(i==2):
            return "take-damage"
        elif(i==3):
            return "instant-death"
    return -1

# Generate the XML file
def generate(mapSizeX, mapSizeY, terrainArray, itemsArray, numItemsToAdd, numAreaEffectsToAdd, outputFileName):
    root = Element("map")
    root.set('width', str(mapSizeX))
    root.set('height', str(mapSizeY))

    for x in range(0, mapSizeX):
        for y in range(0, mapSizeY):
            element = SubElement(root, "tile")

            element.set("x", str(x - mapSizeX/2));
            element.set("y", str(y - mapSizeY/2));
            # Set the terrain
            terrain = SubElement(element, "terrain")
            terrainType = terrainArray[x, y]
            terrain.set("type", terrainType)

            # Add an item (if applicable)
            itemType = itemsArray[x, y]
            if (itemType['id'] != -1):
                item = SubElement(element, "item")
                item.set("id", str(itemType['id']))
                item.set("type", itemType['type'])

            # Add an area effec (if applicable)
            areaEffectType = getAreaEffectType(mapSizeX * mapSizeY, numAreaEffectsToAdd)
            if(areaEffectType != -1 and itemType['id'] == -1):
                areaEffect = SubElement(element, "area-effect")
                areaEffect.set("type", areaEffectType)

    # Write to the file
    outputFile = open(outputFileName + ".xml", "w")
    outputFile.write(prettify(root))


if __name__ == "__main__":
    #read the map file
    with open("map.txt") as f:
        content = f.readlines()

    # Extract the width and height from the first two lines and remove them from the list
    mapWidth = int(content[0])
    mapHeight = int(content[1])
    content.pop(0)
    content.pop(0)

    # Get the terrain from the file
    terrain = getTerrain(content, mapWidth, mapHeight);
    for x in range(0, 51):
        content.pop(0)

    items = getItems(content, mapWidth, mapHeight);
    # TODO: Generate items and area effects in the same way (non random in a text file)

    # Generate the xml file
    generate(mapWidth, mapHeight, terrain, items, 20, 40, "default_map")
    print "Map successfully created!"
