export interface Task {
    id?:number;
    title:string;
    description?:string;
    status:string;
    dueDate:Date;
    user_id?:number;
}