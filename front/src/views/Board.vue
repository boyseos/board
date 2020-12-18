<template>
    <div>
        <table style="margin: auto">
            <tr>
                <th v-for="th in boardTableTh" :key="th">
                    {{th}}
                </th>
            </tr>
            <tr v-for="row in boardModel" :key="row.seq">
                <th v-for="th in Object.keys(boardTableTh)" :key="th">
                    {{row[th]}}
                </th>
            </tr>
        </table>
        <div>글쓰기</div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    beforeMount(){
        this.getBoardByPage(1)
        axios.get('http://localhost:8000/board/count')
            .then(data=>{
                data.status === 200
                    ? this.boardCount = data.data
                    : console.log(data.status)
            })
    },
    data(){
        return {
            boardCount: 0,
            boardTableTh:{
                boardSeq:'번호',
                boardKind:'구분',
                boardTitle:'제목',
                boardWriter:'글쓴이',
                boardRegDate:'작성일',
                boardViewCount:'조회수',
                boardVote:'추천'
                },
            boardModel: {}
        }
    },
    methods:{
        getBoardByPage(page){
            axios.get(`http://localhost:8000/board/page=${page}`)
                .then(data=>{
                    data.status === 200
                        ? this.boardModel = data.data
                        : console.log(data.status)
                })
        }
    }
}
</script>

<style>

</style>