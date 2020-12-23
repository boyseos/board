<template>
    <div>
        <view-box ref="viewBox">
            <template v-slot:types>
                <input ref="test" type="text" value="adfs" @input="log($refs.test.value)">
            </template>
        </view-box>
        <div>
            <span @click="write()">작성</span>
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
            log: console.log
        }
    },
    methods:{
        write(){
            axios.post(
                `http://localhost:8000/board/write`,
                this.$refs.viewBox.boardModel
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