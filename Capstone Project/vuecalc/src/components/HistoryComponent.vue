<template>
    <div class="history">
      <h2>History</h2><br>
  
      <div class="container">
  
        <div class="column">
          <div class="row" v-for="(operation,index) in operations" :key="index">
            <div class="record">
                <p>{{ operation }}</p>
            </div>
          </div>
        </div>
      </div>
  
  
    </div>
  </template>
    
  <script>
  import StorageService from '@/services/StorageService'
  
  export default {
    name: 'HistoryComponent',
    data() {
      return {
        operations: []
      }
    },
    methods: {
      getOperations: async function () {

        try {
            console.log('Hitting web api for operations')
            await StorageService.getOperations().then((response) => {
            console.log('Returned result=' + response.data);
            this.operations = response.data;
            });
        } catch (e) {
            console.log(e)
        }
      }
    },
    beforeMount() {
        this.getOperations()
    }
  }
  </script>
    
    <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped>
  .container {
    display: flex;
  }
  
  .column {
    flex: 1;
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  
  .row {
    flex: 1;
    width: 30%;
    display: flex;
    flex-direction: row;
    border: 1px solid rgba(150, 150, 150, 0.6);
    border-radius: 25px;
    margin: 0.3%;
  }
  
  .record {
    flex: 1;
    background-color: 6;
  }
  
  .column>* {
    font-family: Arial, Helvetica, sans-serif;
    font-size: large;
    font-weight: bold;
  }
  
  .row:hover {
    background-color: rgba(179, 211, 224, 0.6);
  }
 
  </style>
    