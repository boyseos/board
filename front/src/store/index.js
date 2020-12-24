import { createStore } from 'vuex'

export default createStore({
  state: {
    board:{},
    boardTypes:[]
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
