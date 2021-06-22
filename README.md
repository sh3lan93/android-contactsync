# android-contactsync

a simple app that use the `Contacts` database and make simple sync with a local database

### Dependencies plugin 
It's a simple plugin that holds dependencies used in the project and used as an alternative for `buildSrc`. This helps in speed build for more information [Composing builds
](https://docs.gradle.org/current/userguide/composite_builds.html)

### base module 
It's an android module I used to bootstraping the application creation and has a set of bases fragments. activities, viewmodels, services, adapters and viewhodlers.
<br/> This module isn't published yet and i am still working on it. 

### Architecture overview 
I am using repo as it's the only single source of truth that has direct access to deal with database and viewmodel to handle UI states <br/> 
Also, I am using a UseCase for updating underlaying database and I do so beacuase the update could happens from different location:

- `ContactsListFragment`
- `ContactsSyncService` running with two constarins (periodically, when contacts database change happen)
- `ContactsObserver` running only with device perior nougat to detect when contacts database change happen while app is opened 

### Core Dependencies used 
- Room for database
- Koin for DI 
- RxJava
- ViewModel
- Livedata 

 
