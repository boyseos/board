<template>
    <div>
        <view-box>
            <template #title>
                <input v-model="boardModel.boardTitle" type="text">
            </template>
            <template #writer>
                <input v-model="boardModel.boardWriter" type="text">
            </template>
            <template #type>
                <select v-model="boardModel.boardType">
                    <option v-for="type in getBoardTypes"
                    :key="type" :value="type.value">{{type.text}}</option>
                </select>
            </template>
            <template #content>
                <textarea v-model="boardModel.boardContent" cols="30" rows="10"></textarea>
            </template>
        </view-box>
        <div>
            <span @click="write">작성</span>
            <span @click="cancle">취소</span>
        </div>
    </div>
</template>

<script>
import ViewBox from '@/components/ViewBox.vue'
import {mapGetters} from 'vuex'
import axios from 'axios'

export default {
    components:{ViewBox},
    data(){
        return{
            boardModel: {boardType: 1},
        }
    },
    computed:{
        ...mapGetters(
            ['getBoardTypes']
        )
    },
    methods:{
        write(){
            axios.post(
                `http://localhost:8000/board/write`,
                this.boardModel
            )
                .then(data=>{
                    if(data.status === 200){
                        console.log(data.data)
                        this.cancle()
                    } else
                        console.log(data.status)
                })
        },
        cancle(){
            this.$router.push({name:'Board'})
        }
    }

}
</script>

<style>

</style>