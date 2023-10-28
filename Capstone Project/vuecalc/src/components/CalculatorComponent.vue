<template>
  <div class="calculator">
    <h1>AGI Project</h1>
    <h2>Calculator</h2><br>

    <div class="container">

      <div class="column">
        <div class="display-box">{{ current || '0' }}</div>
        <div class="row">
          <div @click="clear" class="big-box">
            <p>Clear</p>
          </div>
          <div @click="divide" class="sign-box">
            <p>/</p>
          </div>
        </div>

        <div class="row">
          <div @click="append('7')" class="box">
            <p>7</p>
          </div>
          <div @click="append('8')" class="box">
            <p>8</p>
          </div>
          <div @click="append('9')" class="box">
            <p>9</p>
          </div>
          <div @click="times" class="sign-box">
            <p>x</p>
          </div>
        </div>

        <div class="row">
          <div @click="append('4')" class="box">
            <p>4</p>
          </div>
          <div @click="append('5')" class="box">
            <p>5</p>
          </div>
          <div @click="append('6')" class="box">
            <p>6</p>
          </div>
          <div @click="minus" class="sign-box">
            <p>-</p>
          </div>
        </div>

        <div class="row">
          <div @click="append('1')" class="box">
            <p>1</p>
          </div>
          <div @click="append('2')" class="box">
            <p>2</p>
          </div>
          <div @click="append('3')" class="box">
            <p>3</p>
          </div>
          <div @click="plus" class="sign-box">
            <p>+</p>
          </div>
        </div>

        <div class="row">
          <div @click="append('0')" class="box">
            <p>0</p>
          </div>
          <div @click="result" class="big-box">
            <p>Result</p>
          </div>
        </div>
      </div>
    </div>


  </div>
  <br>
  <HistoryComponent msg="AGI Project - HistoryComponent" :key="historyKey"/>
</template>
  
<script>
import Calc1 from '@/services/Calc1'
import Calc2 from '@/services/Calc2'
import HistoryComponent from '@/components/HistoryComponent.vue'


export default {
  name: 'CalculatorComponent',
  components: {
    HistoryComponent
  },
  
  data() {
    return {
      historyKey: 0,
      first: '',
      second: '',
      current: '',
      sign: null,
      selected: false,
    }
  },
  methods: {
    calculate: function (num1, num2, opr) {
      switch (opr) {
        case '+':
          try {
            console.log('Hitting web api for addition')
            Calc1.add(num1, num2).then((response) => {
              console.log('Returned result=' + response.data);
              this.clear();
              this.append(response.data);
              this.historyKey++;
            });
          } catch (e) {
            console.log(e)
          }
          break;
        case '-':
          try {
            console.log('Hitting web api for subtraction')
            Calc1.sub(num1, num2).then((response) => {
              console.log('Returned result=' + response.data);
              this.clear();
              this.append(response.data);
              this.historyKey++;
            });
          } catch (e) {
            console.log(e)
          }
          break;
        case 'x':
          try {
            console.log('Hitting web api for multiply')
            Calc2.multiply(num1, num2).then((response) => {
              console.log('Returned result=' + response.data);
              this.clear();
              this.append(response.data);
              this.historyKey++;
            });
          } catch (e) {
            console.log(e)
          }
          break;

        case '/':
          if (num2 === 0) {
            if(confirm('Cannot divide by zero')) this.clear();    
          } else {
            try {
              console.log('Hitting web api for division')
              Calc2.divide(num1, num2).then((response) => {
                console.log('Returned result=' + response.data);
                this.clear();
                this.append(response.data);
                this.historyKey++;
              });
            } catch (e) {
              console.log(e);
            }
          }
          break;

        default:
          console.log('default')
          return this.current;
      }
     
    },
    clear() {
      this.current = '';
      this.first = '';
      this.second = '';
      this.sign = null;
      this.selected = false;
    },
    append(number) {
      if (this.selected) {
        if(confirm('Only 2 numbers are supported')) this.clear();    
        return;
      }
      this.current += number;
      if (/[+\-x/]/.test(this.current.substring(1))) this.second += number;
      else this.first += number;
    },
    plus() {
      if(this.first === '') return;
      this.sign = '+';
      if (this.second !== '') this.selected = true;
      this.current = this.first + this.sign + this.second;
    },
    minus() {
      this.sign = '-';
      if(this.first === '') {
        this.current = '-';
        this.first = '-';
        return;
      }
      if (this.second !== '') this.selected = true;
      this.current = this.first + this.sign + this.second;
    },
    times() {
      if(this.first === '') return;
      this.sign = 'x';
      if (this.second !== '') this.selected = true;
      this.current = this.first + this.sign + this.second;
    },
    divide() {
      if(this.first === '') return;
      this.sign = '/';
      if (this.second !== '') this.selected = true;
      this.current = this.first + this.sign + this.second;
    },
    result() {
      this.current = this.calculate(parseFloat(this.first), parseFloat(this.second), this.sign);
    }
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
}

.box {
  flex: 1;
  background-color: 6;
}

.display-box {
  flex: 1;
  background-color: rgb(240, 240, 240);
  width: 30%;
  padding-top: 1.5%;
  padding-bottom: 1.5%;
}

.big-box {
  flex: 3;
  background-color: rgb(179, 211, 224);
}

.sign-box {
  flex: 1;
  background-color: rgba(239, 175, 57, 0.934);
}

.column>* {
  font-family: Arial, Helvetica, sans-serif;
  font-size: large;
  font-weight: bold;
}

.box:hover {
  background-color: rgba(227, 227, 227, 0.4);
}

.big-box:hover {
  background-color: rgba(179, 211, 224, 0.6);
}

.sign-box:hover {
  background-color: rgba(239, 175, 57, 0.4);
}
</style>
  