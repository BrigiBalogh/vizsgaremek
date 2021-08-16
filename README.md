#Massage
#Időpontfoglaló alkalmazás

##Leírás

Massage szolgáltatásra lehet bejelentkezni ,
új vendéget felvenni, a meglévő időponton módosítani,
törölni.


##Használata

###Guest

A `Guest` entitás a következő attributumokat tartalmazza


* `name` (a vendég neve, nem lehet `null` és üres, legfeljebb 255 karakter)
* `phoneNumber` (a vendég telefonszáma, nem lehet `null` és üres, legfeljebb 255 karakter)
* `medicalCondition` (a vendég egészségi állapota, enum ,értékei( CASUALTY, MALFORMATION, ARTICULAR_PROBLEM, SPINAL_PROBLEM, VERTEBRAL_DISK_PROBLEM, PROBLEM_FREE),legfeljebb 255 karakter)



A következő végpontokon érjük el az entitást

| Http metódus | Vég pont            | Leírás                           |
| ------------ | ------------------- | -------------------------------- |
| GET          | `"api/guests"`      | lekérdezi az összes személyt     |
| GET          | `"api/guests/{id}"` | lekérdez egy személyt id alapján |
| POST         | `"api/guests"`      | létrehoz egy személyt            |
| PUT          | `"api/guests/{id}"` | módosít egy személyt id alapján  |
| DELETE       | `"api/guests/{id}"` | töröl egy személyt id alapján    |


---

### TimeBooker

A `TimeBooker` entitás a következő attributumokat tartalmazza :


* `startTime` (a masszázs kezdete,nem lehet üres és null)
* `endTime` (a masszázs vége, nem lehet üres és null)
* `status` (A vendég státusza a fizetésről,enum, értékei( PAID, NOT_PAID))



A `Guest` és a `TimeBooker` entitás között Kétirányú `@OneToMany`-`@ManyToOne` kapcsolat van.

A következő végpontokon érjük el az entitást

| Http metódus | Vég pont                             | Leírás                                            |
| ------------ | ------------------------------------ | ------------------------------------------------- |
| GET          | `"/api/guests/{id}/time-bookers"`           | lekérdezi az összes  időpontot a hozzátartozó vendéggel együtt      |
| GET          | `"/api/guests/{id}/time-bookers/{timebookerId}"`           | lekérdezi az adott vendég időpontját id alapján        |
| POST         | `"/api/guests/{id}/time-bookers"`           | hozzáad egy új időpontot a vendéghez               |
| PUT          | `"/api/guests/{id}/time-bookers/{timebookerId}"` | Módosítja az adott vendég egy időpontját id alapján |
| DELETE       | `"/api/guests/{id}/time-bookers/{timebookerId}"` | törli az adott vendég egy időpontját id alapján     |

---


