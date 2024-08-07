import cv2
import numpy as np
import face_recognition
import os

# Global variables
path = 'FaceRecognition/images/'
images_list = []  # List of images (matrices)
imageNames = []  # List of image names

# Grab images from the image folder
myList = os.listdir(path)

# Load images
for img_name in myList:
    if img_name.lower().endswith(('.jpeg', '.jpg', '.png', '.bmp', '.tiff')):
        curImg = cv2.imread(os.path.join(path, img_name))
        images_list.append(curImg)
        name = os.path.splitext(img_name)[0]
        imageNames.append(name)

# Function to extract face features
def extractFaceFeatures(Images, names):
    features = []  # List to store features
    count = 1
    for image, name in zip(Images, names):
        # Convert BGR to RGB
        img = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)
        # Detect faces and extract features
        face_encodings = face_recognition.face_encodings(img)
        if face_encodings:  # Check if at least one face was found
            feature = face_encodings[0]
            feature = feature.tolist() + [name]  # Append name to the features
            features.append(feature)
            print(f'{int((count / len(Images)) * 100)} % extracted')
        else:
            print(f"No face found in {name}. Skipping this image.")
        count += 1
    array = np.array(features)
    np.save('Signatures.npy', array)
    print('Signatures saved!')

# Extract features and save to file
extractFaceFeatures(images_list, imageNames)

        