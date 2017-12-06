export class CommentDto {

  constructor (
    public text: string,
    public author: string,
    public email: string,
    public activityId: number
  ) { };

}
