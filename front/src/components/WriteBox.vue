<template>
    <div>
        <table>
            <tr>
                <th>title</th>
                <th>
                    <input v-model="this.board.title" type="text">
                </th>
            </tr>
            <tr>
                <th>writer</th>
                <th>
                    <input v-model="board.writer" type="text">
                </th>
            </tr>
            <tr>
                <th>content</th>
                <th>
                    <textarea v-model="board.content" cols="30" rows="10"></textarea>
                </th>
            </tr>
        </table>
        <div>
            <span @click="write()">작성</span>
            <span @click="cancle()">취소</span>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    data(){
        return{
            board:{
                title:'title',
                content:'content',
                writer:'writer'
            }
        }
    },
    methods:{
        write(){
            axios.post(
                `http://localhost:8000/board/write`,
                {
                    boardTitle: this.board.title,
                    boardContent: this.board.content,
                    boardWriter: this.board.writer
                }
            )
                .then(data=>{
                    if(data.status === 200){
                        this.boardModel = data.data
                        this.cancle()
                    } else
                        console.log(data.status)
                })
        },
        cancle(){
            this.$router.push({name:'Home'})
        }
    }
    
}
</script>

<style>

</style>