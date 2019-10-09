# nutrisi

### Creating the 'food' and 'users' tables for 2 databases 'nutrisi' + 'nutrisitest'

**'Food' table**

```CREATE TABLE food(id SERIAL PRIMARY KEY, name VARCHAR(100), category VARCHAR(100), calories DECIMAL, protein DECIMAL, fat DECIMAL, carbs DECIMAL, fibre DECIMAL, photo VARCHAR(1000));```

**'Users' table**

```CREATE TABLE users(id SERIAL PRIMARY KEY, name VARCHAR(100), email VARCHAR(100), password VARCHAR(100));```

### Starter 'food' table data

```
1	"walnut"	"nut"	        654.0	 15.23	65.21	13.71	6.7   https://source.unsplash.com/FT0fNHmv4A0/400x400

2	"grape"	        "fruit"	        69.0	0.72	0.16	18.1	0.9   https://source.unsplash.com/vGQ49l9I4EE/400x400
3	"parsnip"	"vegetable"	75.0	1.2	0.3	17.99	4.9   https://source.unsplash.com/Bt-T8tSfBkM/400x400
4	"chickpea"	"legume"	364.0	19.0	6.0	61.0	17.0  https://source.unsplash.com/3qjblmWiW98/400x400
5	"pumpkin seed"	"seed"          559.0	30.23	49.05	10.71	6.0   https://source.unsplash.com/J8_U_vokuKk/400x400
6       "raisin"        "fruit"         299.0   3.07    0.46    79.18   3.7   https://source.unsplash.com/KZri3sDZJgg/400x400    "low fat"    "200-299"    "0-9"    "19+"    "0-9"
7       "avocado"       "fruit"         160.0   2.0     14.66   8.53    6.7   https://source.unsplash.com/AWMWcR3hQUg/400x400    "low carb"    "100-199"    "0-9"    "0-9"    "10-19"
8       "duck"          "meat"          404.0   11.49   39.34    0.0    0.0   https://source.unsplash.com/7i_YGMy_-Ho/400x400    "low carb"    "400-499"    "10-19"    "0-9"    "19+"
```

### Using photos from Unsplash

1) Make sure your TablePlus database has a 'photo' column with ```varchar(1000)``` as its data type
2) Input ```https://source.unsplash.com/{PHOTO ID}/{WIDTHxHEIGHT}``` into a database row
3) Go to desired photo on Unsplash website and go on share and copy ```PHOTO ID``` portion of direct link i.e. looks like this: ```FT0fNHmv4A0```
4) Replace ```PHOTO ID``` with ```PHOTO ID``` portion of direct link, so your link now looks like this: ```https://source.unsplash.com/FT0fNHmv4A0/```
4) Add ```WIDTHxHEIGHT``` to this e.g. ```400x400```
5) Your link should now look like this: ```https://source.unsplash.com/FT0fNHmv4A0/400x400```
6) Put the link in your database row and save (Command + S)!
7) Re-run your app and see the result

### Cloning from a specific branch to play around with the app

```git clone -b <branch> <remote_repo>``` e.g. ```git clone -b basicmvp https://github.com/ParisaTork/nutrisi.git```

N.B. If you have a local repo of the same name, you can either:

Option 1) Rename the local repo so it doesn't conflict with the remote repo e.g. rename nutrisi to nutrisi1. Do this if **you don't want to push anything anymore from that repo**. You can now clone the new remote repo from Git without any regrets about deleting a local repo. 

Option 2 (risky) ) Duplicate the local repo/push it to a new branch on Git. 
**MAKE SURE YOU HAVE A COPY OF IT SOMEWHERE!**  This is because Git won't let you clone a remote repo to your machine if it has the same name as a local repo on your machine. You can now delete the local repo and clone from the specific branch you want to play around with.

[Back to Nutrisi Wiki Homepage](https://github.com/ParisaTork/nutrisi/wiki)
