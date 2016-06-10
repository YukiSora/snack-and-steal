import sys
from PIL import Image
from PIL import ImageDraw

if len(sys.argv) < 2:
    print('python ImageConverter.py convert sourceImage.png')
    print('python ImageConverter.py test sourceImage.png')
    sys.exit()

sourceImage = Image.open(sys.argv[2])
if sys.argv[1] == 'convert':
    with open('path', mode = 'w') as f:
        for h in range(0, sourceImage.size[1]):
            for w in range(0, sourceImage.size[0]):
                pixel = sourceImage.getpixel((w, h))
                if pixel == (255, 255, 255, 0):
                    f.write('0')
                else:
                    f.write('1')
            f.write('\n')
elif sys.argv[1] == 'test':
    testImage = Image.new('RGB', sourceImage.size)
    draw = ImageDraw.Draw(testImage)

    for h in range(0, sourceImage.size[1]):
        for w in range(0, sourceImage.size[0]):
            pixel = sourceImage.getpixel((w, h))
            if not pixel == (255, 255, 255, 0):
                draw.point((w, h), fill = (0, 0, 0, 0))
            else:
                draw.point((w, h), fill = pixel)

    testImage.save('test.jpg', 'jpeg');