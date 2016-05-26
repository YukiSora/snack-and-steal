from PIL import Image
from PIL import ImageDraw

image = Image.open('image/small_map_walking_area.png')

poi = Image.open('code.jpg')
draw = ImageDraw.Draw(poi)

for h in range(0, image.size[1]):
    for w in range(0, image.size[0]):
        pixel = image.getpixel((w, h))
        if pixel == (255, 255, 255, 0):
            print(0, end = "")
        else:
            print(1, end="")
    print()

# image test
# poi = Image.open('code.jpg')
# draw = ImageDraw.Draw(poi)

# for h in range(0, image.size[1]):
#     for w in range(0, image.size[0]):
#         pixel = image.getpixel((w, h))
#         if not pixel == (255, 255, 255, 0):
#             draw.point((w, h), fill=(0, 0, 0, 0))
#         else:
#             draw.point((w, h), fill=pixel)

# poi.save('code.jpg', 'jpeg');