# -*- coding:utf-8 -*-

def fn_glactus_says(hero, *planets, **weapons):
    print ("""Technical Information:
    Type of [hero : %s] [planets : %s] [weapons : %s]""" %( type(hero), type(planets), type(weapons)  ))
    print ("--------------------------------------------------")    
    
    print ("I know, %s considers himself a hero !!!" %(hero))
    if planets:
        print ("But no one can save", end=" ")
        for planet in planets:
            print (planet, end=" ")
    
    if bool(weapons):
        print ("\nNo weapon can harm me!!! Be it", end=" ")
        for kw, vw in weapons.items():
            print (",", vw, "in", kw, end=" ")
    print ("\n")    



fn_glactus_says("Blade")
fn_glactus_says("Dr. Strange", "earth")
fn_glactus_says("Superman", "earth", "krypton")
fn_glactus_says("Cpt. Marvel", "earth", "mars", "venus", eyes="lazer", hand="rays", legs="kick")