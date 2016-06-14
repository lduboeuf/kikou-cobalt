var TeamList = {

        STORE_NAME : 'peoples',

        getAll: function(){
            return cobalt.storage.get(this.STORE_NAME);
        },

        store: function(peoples){
            cobalt.storage.set(this.STORE_NAME, peoples);
        },

        remove: function(id){
            var peoples = this.getAll();
            for (var i=0; i < peoples.length; i++){
                if (peoples[i].id == id){
                    peoples.splice(i, 1);
                    break;
                }
            }
            
            this.store(peoples);
        },


        save: function(people){

            var peoples = this.getAll();
            if (typeof people.id == 'undefined'){
                var id = 1;
                if(peoples.length>0){
                    var lastElement = peoples[peoples.length-1];
                    if (typeof lastElement.id != 'undefined'){
                        id = lastElement.id;
                    }
                    
                }
                people.id = id;
                peoples.push(people);
            }else { //update mode
                for (var i=0; i < peoples.length; i++){
                    if (peoples[i].id == people.id){
                        peoples[i].name = people.name;
                        break;
                    }
                }
            }
            this.store(peoples);
        }


    }