<!-- Downladed from  https://github.com/BosNaufal/vue2-autocomplete
  edited to fit the user selector of CollectiveTweet -->

<template>
  <div class="autocomplete-wrapper">
    <input  type="text"
            :id="id"
            class="form-control autocomplete-inputs"
            :placeholder="placeholder"
            v-model="type"
            @input="input(type)"
            @dblclick="showAll"
            @blur="hideAll"
            @keydown="keydown"
            @focus="focus"
            autocomplete="off" />

    <div :class="(className ? className + '-list ' : '') + 'autocomplete transition autocomplete-list'" v-show="showList">
      <ul>
        <li v-for="(data, i) in json"
            transition="showAll"
            :class="activeClass(i)">

          <a  href="#"
              @click.prevent="selectList(data)"
              @mousemove="mousemove(i)">
            <b>{{ data[anchor] }}</b>
            <span>{{ data[label] }}</span>
          </a>

        </li>
      </ul>
    </div>
  </div>
</template>


<script>
  /* eslint-disable */

  /*! Copyright (c) 2016 Naufal Rabbani (http://github.com/BosNaufal)
  * Licensed Under MIT (http://opensource.org/licenses/MIT)
  *
  * Vue 2 Autocomplete @ Version 0.0.1
  *
  */

  /*!
  *  javascript-debounce 1.0.0
  *
  *  A lightweight, dependency-free JavaScript module for debouncing functions based on David Walsh's debounce function.
  *
  *  Source code available at: https://github.com/jgarber623/javascript-debounce
  *
  *  (c) 2015-present Jason Garber (http://sixtwothree.org)
  *
  *  javascript-debounce may be freely distributed under the MIT license.
  */

  var debounce = function(callback, delay) {
    var timeout;
    return function() {
      var context = this, args = arguments;
      clearTimeout(timeout);
      timeout = setTimeout(function() {
        callback.apply(context, args);
      }, delay);
    };
  };

  export default {

    props: {
      id: String,
      className: String,
      placeholder: String,

      // Intial Value
      initValue: {
        type: String,
        default: ""
      },

      // Anchor of list
      anchor: {
        type: String,
        required: true
      },

      // Label of list
      label: String,

      // Debounce time
      debounce: Number,

      // axios URL will be fetched
      url: {
        type: String,
        required: true
      },

      // query param
      param: {
        type: String,
        default: 'q'
      },

      // Custom Params
      customParams: Object,

      // minimum length
      min: {
        type: Number,
        default: 0
      },

      // Process the result before retrieveng the result array.
      process: Function,

      // Callback
      onInput: Function,
      onShow: Function,
      onBlur: Function,
      onHide: Function,
      onFocus: Function,
      onBeforeRequest: Function,
      onRequestLoaded: Function,

    },

    data() {
      return {
        showList: false,
        type: "",
        json: [],
        focusList: ""
      };
    },


    methods: {

      // Netralize Autocomplete
      clearInput() {
        this.showList = false
        this.type = ""
        this.json = []
        this.focusList = ""
      },

      // Get the original data
      cleanUp(data){
        return JSON.parse(JSON.stringify(data));
      },

      input(val){
        if(this.json.length > 0) {
            this.showList = true;
        } else {
          this.hideAll()
        }

        // Callback Event
        this.onInput ? this.onInput(val) : null

        // Debounce the "getData" method.
        if(!this.debouncedGetData || this.debounce !== this.oldDebounce) {
          this.oldDebounce = this.debounce;
          this.debouncedGetData = this.debounce ? debounce(this.getData.bind(this), this.debounce) : this.getData;
        }

        // Get The Data
        this.debouncedGetData(val)
      },

      showAll(){
        this.json = [];

        this.getData("")

        // Callback Event
        this.onShow ? this.onShow() : null

        this.showList = true;
      },

      hideAll(e){
        // Callback Event
        this.onBlur ? this.onBlur(e) : null

        setTimeout(() => {

          // Callback Event
          this.onHide ? this.onHide() : null

          this.showList = false;
        },250);
      },

      focus(e){
        this.focusList = 0;

        // Callback Event
        this.onFocus ? this.onFocus(e) : null

        // Show when seleceted
        this.showAll()
      },

      mousemove(i){
        this.focusList = i;
      },

      keydown(e){
        let key = e.keyCode;

        // Disable when list isn't showing up
        if(!this.showList) return;

        switch (key) {
          case 40: //down
            this.focusList++;
          break;
          case 38: //up
            this.focusList--;
          break;
          case 13: //enter
            this.selectList(this.json[this.focusList])
            this.showList = false;
          break;
          case 27: //esc
            this.showList = false;
          break;
        }

        // When cursor out of range
        let listLength = this.json.length - 1;
        this.focusList = this.focusList > listLength ? 0 : this.focusList < 0 ? listLength : this.focusList;

      },

      activeClass(i){
        return {
          'focus-list' : i == this.focusList
        };
      },

      selectList(data){
        let clean = this.cleanUp(data);

        // Put the selected data to type (model)
        this.type = clean[this.anchor];

        this.showList = false;

        /**
        * Callback Event
        * Deep clone of the original object
        */
        this.$emit('select',clean)
      },

      getData(val){

        if (val.length < this.min) return;

        if(this.url != null){
          // Callback Event
          this.onBeforeRequest ? this.onBeforeRequest(val) : null

          var params = {}
          params[this.param] = val

          this.axios.get(this.url,{
            params: params
          }).then((response) => {
            this.onRequestLoaded ? this.onRequestLoaded(response.data) : null
            this.json = this.process ? self.process(response.data) : response.data;
          })
        }
      },

      setValue(val) {
        this.type = val
      }
    },

    created(){
      // Sync parent model with initValue Props
      this.type = this.initValue ? this.initValue : null
    }

  }
</script>

<style scoped>

.transition, .autocomplete, .showAll-transition, .autocomplete ul, .autocomplete ul li a{
  transition:all 0.3s ease-out;
  -moz-transition:all 0.3s ease-out;
  -webkit-transition:all 0.3s ease-out;
  -o-transition:all 0.3s ease-out;
}

.autocomplete-list ul {
  max-height: 250px;
  width: 100%;
  overflow-y: auto;
  z-index: 1100;
}

.autocomplete ul{
  font-family: sans-serif;
  position: absolute;
  list-style: none;
  background: #f8f8f8;
  padding: 10px 0;
  margin: 0;
  display: inline-block;
  min-width: 15%;
  margin-top: 10px;
}

.autocomplete ul:before{
  content: "";
  display: block;
  position: absolute;
  height: 0;
  width: 0;
  border: 10px solid transparent;
  border-bottom: 10px solid #f8f8f8;
  left: 46%;
  top: -20px
}

.autocomplete ul li a{
  text-decoration: none;
  display: block;
  background: #f8f8f8;
  color: #2b2b2b;
  padding: 5px;
  padding-left: 10px;
}

.autocomplete ul li a:hover, .autocomplete ul li.focus-list a{
  color: white;
  background: #2F9AF7;
}

.autocomplete ul li a span{
  display: block;
  margin-top: 3px;
  color: grey;
  font-size: 13px;
}

.autocomplete ul li a:hover span, .autocomplete ul li.focus-list a span{
  color: white;
}

.showAll-transition{
  opacity: 1;
  height: 50px;
  overflow: hidden;
}

.showAll-enter{
  opacity: 0.3;
  height: 0;
}

.showAll-leave{
  display: none;
}

</style>
