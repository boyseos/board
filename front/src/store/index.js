import { createStore } from 'vuex'

export default createStore({
  state: {
    board:{},
    boardTypes:[
      {value:1, text:'자유'},
      {value:2, text:'Q&A'},
      {value:4, text:'질문'},
      {value:8, text:'Test'}
  ],
  },
  getters:{
    getBoardTypes(state){
      return state.boardTypes
    }
  }
  ,
  mutations: {
    setBoardTypes(state,payload){
      state.boardTypes = payload
    }
  },
  actions: {
    addCounter(context){
      return context.commit('addCounter')
    }
  },
  modules: {
  }
})
