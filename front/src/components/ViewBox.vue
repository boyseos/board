<template>
    <div>
        <table>
            <tr>
                <th>title</th>
                <th>
                    <slot name="title"/>
                </th>
            </tr>
            <tr>
                <th>type</th>
                <th>
                    <slot name="type"/>
                </th>
            </tr>
            <tr>
                <th>writer</th>
                <th>
                    <slot name="writer"/>
                </th>
            </tr>
            <tr><slot name="types"></slot></tr>
            <tr>
                <th>content</th>
                <th>
                    <slot name="content"/>
                </th>
            </tr>
        </table>
    </div>
</template>

<script>
//import axios from 'axios'

export default {
    props:{
    //     value:{
    //         type: Object,
    //         required: false,
    //         default(){
    //             return {
    //                 boardTitle: '',
    //                 boardWriter: '',
    //                 boardContent: ''
    //             }
    //         }
    //     },
    },
    data(){
        return{
            //boardModel: this.value
        }
    },
    methods:{
        // update(){
        //     this.$emit('update:boardProps', this.boardModel)
        // }
    }
    
}
</script>

<style>

</style>