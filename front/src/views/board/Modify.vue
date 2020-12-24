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
                    <option v-for="type in $store.getters.getBoardTypes"
                    :key="type" :value="type.value">{{type.text}}</option>
                </select>
            </template>
            <template #content>
                <textarea v-model="boardModel.boardContent" cols="30" rows="10"></textarea>
            </template>
        </view-box>
        <div>
            <span @click="modify()">수정</span>
            <span @click="cancle()">취소</span>
        </div>
    </div>
</template>

<script>
import ViewBox from '@/components/ViewBox.vue'
import axios from 'axios'

export default {
    components:{ViewBox},
    data(){
        return{
            boardModel: this.$route.params
        }
    },
    methods:{
        modify(){
            axios.patch(
                `http://localhost:8000/board/patch`,
                this.boardModel
            )
                .then(data=>{
                    if(data.status === 200){
                        this.$router.push({name:'View',params: this.boardModel})
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