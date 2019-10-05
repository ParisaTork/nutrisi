# nutrisi

### Creating the tables for 2 databases 'nutrisi' + 'nutrisitest'

```CREATE TABLE food(id SERIAL PRIMARY KEY, name VARCHAR(100), category VARCHAR(100), calories DECIMAL, protein DECIMAL, fat DECIMAL, carbs DECIMAL, fibre DECIMAL, photo VARCHAR(1000));```

### Starter 'food' table data

```
1	"walnut"	"nut"	        654.0	 15.23	65.21	13.71	6.7   https://source.unsplash.com/FT0fNHmv4A0/400x400

2	"grape"	        "fruit"	        69.0	0.72	0.16	18.1	0.9   https://source.unsplash.com/vGQ49l9I4EE/400x400
3	"parsnip"	"vegetable"	75.0	1.2	0.3	17.99	4.9   https://source.unsplash.com/Bt-T8tSfBkM/400x400
4	"chickpea"	"legume"	364.0	19.0	6.0	61.0	17.0  https://source.unsplash.com/3qjblmWiW98/400x400
5	"pumpkin seed"	"seed"          559.0	30.23	49.05	10.71	6.0   https://source.unsplash.com/J8_U_vokuKk/400x400
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

N.B. If you have a local repo of the same name, duplicate it/push it to a new branch on Git. **MAKE SURE YOU HAVE A COPY OF IT SOMEWHERE!** You can now delete the local repo and clone from the specific branch you want to play around with.

[Back to Nutrisi Wiki Homepage](https://github.com/ParisaTork/nutrisi/wiki)
